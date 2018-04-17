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

  private String serialize(HashMap<String,String> values) {
	String result = "";
	int valueSize = values.size();
	System.out.println("[s] int : "+ valueSize + ", String : " + Integer.toString(valueSize).length());
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
	String hash = serialize(values);
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
      client.insert("table", "key3", values);
    } finally {
      client.shutdown();
    }
  }
}
