/**
 * Copyright (c) 2016 YCSB contributors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */

package com.yahoo.ycsb.db.leveldb;

import com.yahoo.ycsb.ByteIterator;
import com.yahoo.ycsb.StringByteIterator;
import com.yahoo.ycsb.DB;
import com.yahoo.ycsb.DBException;
import com.yahoo.ycsb.Status;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.*;
import java.util.Map.Entry;

/**
 * LevelDB client for YCSB framework.
 *
 * <p>
 * Default properties to set:
 * </p>
 * <ul>
 * See README.md
 * </ul>
 *
 */

public class YCSBLevelDBClient extends DB {
//  private static final Logger logger = Logger.getLogger(YCSBLevelDBClient.class.getName());

  private final ManagedChannel channel;
  private final LevelDBGrpc.LevelDBBlockingStub blockingStub;
  private static final int BYTE_BUFFER_SIZE = 4096;

  public YCSBLevelDBClient() {
    channel = ManagedChannelBuilder.forAddress("localhost", 30030)
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
  
  private String serialize(Map<String, ByteIterator> values) {
    int valueSize = values.size();
    String result = inttostring(valueSize);
    for (Map.Entry<String, ByteIterator> entry : values.entrySet()) {
      String key = entry.getKey();
      result = result + inttostring(key.length());
      result = result + key;
      String val = entry.getValue().toString();
      result = result + inttostring(val.length());
      result = result + val;
    }
    return result;
  }

  private Map<String, ByteIterator> deserialize(String values) {
    if(values.length() < 8) {
      return new HashMap<String, ByteIterator>();
    }
    int valueSize = Integer.parseInt(values.substring(0, 8), 16);
    Map<String, ByteIterator> result = new HashMap<String, ByteIterator>();
    int starting = 8;
    for (int i=0; i<valueSize; i++) {
      int keyLength = Integer.parseInt(values.substring(starting, starting+8), 16);
      starting = starting + 8;
      String key = values.substring(starting, starting + keyLength);
      starting = starting + keyLength;
      int valueLength = Integer.parseInt(values.substring(starting, starting+8), 16);
      starting = starting + 8; 
      String valueS = values.substring(starting, starting + valueLength);
      ByteIterator value = new StringByteIterator(valueS);
      starting = starting + valueLength;
      
      result.put(key, value);
    }
    return result;
  }

  /**
   * Initialize any state for this DB. called once per DB instance;
   */
  @Override
  public void init() throws DBException {
    System.out.println("Initializing :)");

    YCSBLevelDBClient client = new YCSBLevelDBClient();
  }

  /**
   * Read a record from the database. Each field/value pair from the result will be stored in a
   * Map.
   *
   * @param table
   *          The name of the table
   * @param key
   *          The record key of the record to read.
   * @param fields
   *          The list of fields to read, or null for all of them
   * @param result
   *          A Map of field/value pairs for the result
   * @return Zero on success, a non-zero error code on error or "not found".
   */
  @Override
  public Status read(String table, String key, Set<String> fields,
      Map<String, ByteIterator> result) {
    ReadM request = ReadM.newBuilder().setTable(table).setKey(key).build();
    ReadResult response;
    try {
      response = blockingStub.read(request);
      Map<String, ByteIterator> output = deserialize(response.getOutput());
      if(fields == null) {
        return Status.OK;
      }
      for(String s : fields) {
        ByteIterator v = output.get(s);
        result.put(s, v);
      }
    } catch (StatusRuntimeException e) {
      return Status.ERROR;
    }
    if (result.isEmpty()) {
      return Status.NOT_FOUND;
    }
    return Status.OK;
  }

  /**
   * Perform a range scan for a set of records in the database. Each field/value pair from the
   * result will be stored in a Map.
   *
   * @param table
   *          The name of the table
   * @param startkey
   *          The record key of the first record to read.
   * @param recordcount
   *          The number of records to read
   * @param fields
   *          The list of fields to read, or null for all of them
   * @param result
   *          A Vector of Maps, where each HashMap is a set field/value pairs for one record
   * @return Zero on success, a non-zero error code on error. See this class's description for a
   *         discussion of error codes.
   */ 
  @Override
  public Status scan(String table, String startkey, int recordcount, Set<String> fields,
      Vector<HashMap<String, ByteIterator>> result) {
    ScanM request = ScanM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.scan(request);
    } catch (StatusRuntimeException e) {
      return Status.ERROR;
    }
    return Status.OK;
  }

  /**
   * Update a record in the database. Any field/value pairs in the specified values Map will be
   * written into the record with the specified record key, overwriting any existing values with the
   * same field name.
   *
   * @param table
   *          The name of the table
   * @param key
   *          The record key of the record to write.
   * @param values
   *          A Map of field/value pairs to update in the record
   * @return Zero on success, a non-zero error code on error. See this class's description for a
   *         discussion of error codes.
   */ 
  @Override
  public Status update(String table, String key, Map<String, ByteIterator> values) {
    String hash = serialize(values);
    UpdateM request = UpdateM.newBuilder().setTable(table).setKey(key).setValues(hash).build();
    Result response;
    try {
      response = blockingStub.update(request);
    } catch (StatusRuntimeException e) {
      return Status.ERROR;
    }
    return Status.OK;
  }

  /**
   * Insert a record in the database. Any field/value pairs in the specified values Map will be
   * written into the record with the specified record key.
   *
   * @param table
   *          The name of the table
   * @param key
   *          The record key of the record to insert.
   * @param values
   *          A Map of field/value pairs to insert in the record
   * @return Zero on success, a non-zero error code on error. See this class's description for a
   *         discussion of error codes.
   */
  @Override
  public Status insert(String table, String key, Map<String, ByteIterator> values) {
    String hash = serialize(values);
    InsertM request = InsertM.newBuilder().setTable(table).setKey(key).setValues(hash).build();
    Result response;
    try {
      response = blockingStub.insert(request);
    } catch (StatusRuntimeException e) {
      return Status.ERROR;
    }
    return Status.OK;
  }

  /**
   * Delete a record from the database.
   *
   * @param table
   *          The name of table
   * @param key
   *          The record key of the record to delete
   * @return Zero on success, otherwise non-zero
   */
  @Override
  public Status delete(String table, String key) {
    DeleteM request = DeleteM.newBuilder().setTable(table).build();
    Result response;
    try {
      response = blockingStub.ldelete(request);
    } catch (StatusRuntimeException e) {
      return Status.ERROR;
    }
    return Status.OK;
  }
}
