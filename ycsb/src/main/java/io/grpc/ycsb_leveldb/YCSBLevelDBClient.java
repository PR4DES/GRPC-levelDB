package io.grpc.ycsb_leveldb;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class YCSBLevelDBClient {
  private static final Logger logger = Logger.getLogger(YCSBLevelDBClient.class.getName());

  private final ManagedChannel channel;
  private final LevelDBGrpc.LevelDBBlockingStub blockingStub;

  public YCSBLevelDBClient(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext(true)
        .build();
    blockingStub = LevelDBGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  public int read(String table) {
    ReadM request = ReadM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.read(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return 1;
    }
	return 0;
  }

  public int scan(String table) {
    ScanM request = ScanM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.scan(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return 1;
    }
	return 0;
  }

  public int update(String table) {
    UpdateM request = UpdateM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.update(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return 1;
    }
	return 0;
  }

  public int insert(String table) {
    InsertM request = InsertM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.insert(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return 1;
    }
	return 0;
  }

  public int delete(String table) {
    DeleteM request = DeleteM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.ldelete(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return 1;
    }
	return 0;
  }

  public static void main(String[] args) throws Exception {
    YCSBLevelDBClient client = new YCSBLevelDBClient("localhost", 30030);
    try {
      client.read("asdf");
    } finally {
      client.shutdown();
    }
  }
}
