package io.grpc.ycsb_leveldb;

import com.yahoo.ycsb.ByteArrayByteIterator;
import com.yahoo.ycsb.ByteIterator;
import com.yahoo.ycsb.DB;
import com.yahoo.ycsb.DBException;
import com.google.protobuf.ByteString;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;

public class YCSBLevelDBClient {
//  private static final Logger logger = Logger.getLogger(YCSBLevelDBClient.class.getName());

  private final ManagedChannel channel;
  private final LevelDBGrpc.LevelDBBlockingStub blockingStub;
  private static final int BYTE_BUFFER_SIZE = 4096;

  public YCSBLevelDBClient(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext(true)
        .build();
    blockingStub = LevelDBGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }
  public int byteToint(byte[] arr){
	  return (arr[0] & 0xff)<<24 | (arr[1] & 0xff)<<16 |
		  (arr[2] & 0xff)<<8 | (arr[3] & 0xff);
  }

  private byte[] serialize(HashMap<String, String> values) {
	  ByteBuffer buf = ByteBuffer.allocate(BYTE_BUFFER_SIZE);
	  System.out.println("[s](byte) values.size() // " + (byte) values.size());
	  buf.put((byte) values.size());
	  for (Map.Entry<String, String> entry : values.entrySet()) {
		  buf.put((byte) entry.getKey().length());
		  buf.put(entry.getKey().getBytes());
		  buf.put((byte) entry.getValue().length());
		  buf.put((entry.getValue().getBytes()));
	  }
	  System.out.println("[s]buf.position() // " + buf.array());
	  byte[] result = new byte[buf.position()];
	  buf.get(result,0,buf.position());
	  System.out.println("[s]result.length // " + result.length);
	  return result;
  }

  private HashMap<String, String> deserialize(byte[] bytes) {
	  HashMap<String, String> result = new HashMap<String, String>();
	  System.out.println("[d]bytes.length // " + bytes.length);
	  ByteBuffer buf = ByteBuffer.allocate(BYTE_BUFFER_SIZE);
	  buf.get(bytes);
	  System.out.println("[d]buf.position() // " + buf.array());
//	  int count = buf.getInt();
	  byte[] cnt = new byte[4];
	  buf.get(cnt, 0, 4);
	  int count = byteToint(cnt);	  
	  System.out.println("[d]count // " + cnt[0]);
	  for (int i = 0; i < count; i++) {
		  int keyLength = buf.getInt();
		  byte[] keyBytes = new byte[keyLength];
		  buf.get(keyBytes, buf.position(), keyLength);

		  int valueLength = buf.getInt();
		  byte[] valueBytes = new byte[valueLength];
		  buf.get(valueBytes, buf.position(), valueLength);

		  result.put(new String(keyBytes,0,keyBytes.length), new String(valueBytes,0,valueBytes.length));
	  }
	  System.out.println("[d]result.size // " + result.size());
	  return result;
  }

  public void init() throws DBException {
	System.out.println("Initializing...");

	YCSBLevelDBClient client = new YCSBLevelDBClient("localhost", 30030);
	HashMap<String,String> values = new HashMap<String,String>();
	values.put("k1","v1");
    client.insert("table", "key1", values);
  }

  public int read(String table, String key) {
    ReadM request = ReadM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.read(request);
    } catch (StatusRuntimeException e) {
      return 1;
    }
	return response.getResult();
  }

  public int scan(String table, String key) {
    ScanM request = ScanM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.scan(request);
    } catch (StatusRuntimeException e) {
      return 1;
    }
	return response.getResult();
  }

  public int update(String table, String key) {
    UpdateM request = UpdateM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.update(request);
    } catch (StatusRuntimeException e) {
      return 1;
    }
	return response.getResult();
  }

  public int insert(String table, String key, HashMap<String,String> values) {
	System.out.println(values.values());
	byte[] serialized = serialize(values);
	HashMap<String,String> de = new HashMap<String,String>();
	de = deserialize(serialized);
	System.out.println(de.size());
	ByteString hash = ByteString.copyFrom(serialized);
	InsertM request = InsertM.newBuilder().setTable(table).setKey(key).setHash(hash).build();
    Result response;
    try {
      response = blockingStub.insert(request);
    } catch (StatusRuntimeException e) {
      return 1;
    }
	return response.getResult();
  }

  public int delete(String table, String key) {
    DeleteM request = DeleteM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.ldelete(request);
    } catch (StatusRuntimeException e) {
//      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return 1;
    }
	return response.getResult();
  }

  public static void main(String[] args) throws Exception {
    YCSBLevelDBClient client = new YCSBLevelDBClient("localhost", 30030);
	HashMap<String, String> values = new HashMap<String,String>();
	values.put("k1","v1");
	values.put("k2","v2");
	values.put("k3","v3");
    try {
      client.insert("table", "key2", values);
    } finally {
      client.shutdown();
    }
  }
}
