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

public class YCSBLevelDBClient extends DB {
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

  private String inttostring(int val) {
	String result = Integer.toHexString(val);
	while(result.length() != 8) {
		result = "0"+ result;
	}
	return result;
  }
  
  private String serialize(HashMap<String,String> values) {
	int valueSize = values.size();
	String result = inttostring(valueSize);
	for (Map.Entry<String,String> entry : values.entrySet()) {
		result = result + inttostring(entry.getKey().length());
		result = result + entry.getKey();
		result = result + inttostring(entry.getValue().length());
		result = result + entry.getValue();
	}
	return result;
  }

  private HashMap<String,String> deserialize(String values) {
	  int valueSize = Integer.parseInt(values.substring(0,8), 16);
	  HashMap<String, String> result = new HashMap<String,String>();
	  int starting = 8;
	  for (int i=0; i<valueSize; i++) {
		int keyLength = Integer.parseInt(values.substring(starting, starting+8), 16);
		starting = starting + 8;
		String key = values.substring(starting,starting + keyLength);
		starting = starting + keyLength;
		int valueLength = Integer.parseInt(values.substring(starting, starting+8), 16);
		starting = starting + 8; 
		String value = values.substring(starting, starting + valueLength);
		starting = starting + valueLength;
		result.put(key, value);
	  }
	  return result;
  }

  @Override
  public void init() throws DBException {
	System.out.println("Initializing...");

	YCSBLevelDBClient client = new YCSBLevelDBClient("localhost", 30030);
//	HashMap<String,String> values = new HashMap<String,String>();
//	values.put("k1","v1");
  //  client.insert("table", "key1", values);
  }

  @Override
  public int read(String table, String key, Set<String> fields, HashMap<String,String> result) {
    ReadM request = ReadM.newBuilder().setTable(table).setKey(key).build();
    ReadResult response;
    try {
      response = blockingStub.read(request);
	  HashMap<String,String> output = deserialize(response.getOutput());
	  for(String s : fields) {
		  String v = output.get(s);
		  if(v != null) result.put(s,v);
	  }
    } catch (StatusRuntimeException e) {
      return 1;
    }
	return response.getResult();
  }

  @Override
  public int scan(String table, String startkey, int recordcount, Set<String> fields, Vector<HashMap<String,String>> result) {
    ScanM request = ScanM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.scan(request);
    } catch (StatusRuntimeException e) {
      return 1;
    }
	return response.getResult();
  }

  @Override
  public int update(String table, String key, HashMap<String,String> values) {
	String hash = serialize(values);
	UpdateM request = UpdateM.newBuilder().setTable(table).setKey(key).setValues(hash).build();
    Result response;
    try {
      response = blockingStub.update(request);
    } catch (StatusRuntimeException e) {
      return 1;
    }
	return response.getResult();
  }

  @Override
  public int insert(String table, String key, HashMap<String,String> values) {
	String hash = serialize(values);
	InsertM request = InsertM.newBuilder().setTable(table).setKey(key).setValues(hash).build();
    Result response;
    try {
      response = blockingStub.insert(request);
    } catch (StatusRuntimeException e) {
      return 1;
    }
	return response.getResult();
  }

  @Override
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

/*  public static void main(String[] args) throws Exception {
    YCSBLevelDBClient client = new YCSBLevelDBClient("localhost", 30030);
	HashMap<String, String> values = new HashMap<String,String>();
	values.put("k1","v1");
	values.put("k2","v2");
	values.put("k3","v3");
	Set<String> f= new HashSet<String>();
	f.add("k1");
	f.add("k2");
	HashMap<String,String> result = new HashMap<String,String>();
    try {
      client.insert("table", "key3", values);
	  client.read("table","key3",f, result);
    } finally {
      client.shutdown();
    }
  } */
}
