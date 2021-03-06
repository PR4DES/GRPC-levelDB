// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: leveldb_ycsb.proto

package io.grpc.ycsb_leveldb;

public final class YCSBLeveDB {
  private YCSBLeveDB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ycsbleveldb_ReadM_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ycsbleveldb_ReadM_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ycsbleveldb_ScanM_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ycsbleveldb_ScanM_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ycsbleveldb_UpdateM_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ycsbleveldb_UpdateM_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ycsbleveldb_InsertM_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ycsbleveldb_InsertM_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ycsbleveldb_DeleteM_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ycsbleveldb_DeleteM_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ycsbleveldb_Result_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ycsbleveldb_Result_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ycsbleveldb_ReadResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ycsbleveldb_ReadResult_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022leveldb_ycsb.proto\022\013ycsbleveldb\"#\n\005Rea" +
      "dM\022\r\n\005table\030\001 \001(\t\022\013\n\003key\030\002 \001(\t\"L\n\005ScanM\022" +
      "\r\n\005table\030\001 \001(\t\022\020\n\010startkey\030\002 \001(\t\022\023\n\013reco" +
      "rdcount\030\003 \001(\005\022\r\n\005field\030\004 \001(\t\"5\n\007UpdateM\022" +
      "\r\n\005table\030\001 \001(\t\022\013\n\003key\030\002 \001(\t\022\016\n\006values\030\003 " +
      "\001(\t\"5\n\007InsertM\022\r\n\005table\030\001 \001(\t\022\013\n\003key\030\002 \001" +
      "(\t\022\016\n\006values\030\003 \001(\t\"%\n\007DeleteM\022\r\n\005table\030\001" +
      " \001(\t\022\013\n\003key\030\002 \001(\t\"\030\n\006Result\022\016\n\006result\030\001 " +
      "\001(\005\",\n\nReadResult\022\016\n\006output\030\001 \001(\t\022\016\n\006res" +
      "ult\030\002 \001(\0052\231\002\n\007LevelDB\0225\n\004Read\022\022.ycsbleve",
      "ldb.ReadM\032\027.ycsbleveldb.ReadResult\"\000\0221\n\004" +
      "Scan\022\022.ycsbleveldb.ScanM\032\023.ycsbleveldb.R" +
      "esult\"\000\0225\n\006Update\022\024.ycsbleveldb.UpdateM\032" +
      "\023.ycsbleveldb.Result\"\000\0225\n\006Insert\022\024.ycsbl" +
      "eveldb.InsertM\032\023.ycsbleveldb.Result\"\000\0226\n" +
      "\007Ldelete\022\024.ycsbleveldb.DeleteM\032\023.ycsblev" +
      "eldb.Result\"\000B*\n\024io.grpc.ycsb_leveldbB\nY" +
      "CSBLeveDBP\001\242\002\003LDBb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_ycsbleveldb_ReadM_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ycsbleveldb_ReadM_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ycsbleveldb_ReadM_descriptor,
        new java.lang.String[] { "Table", "Key", });
    internal_static_ycsbleveldb_ScanM_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ycsbleveldb_ScanM_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ycsbleveldb_ScanM_descriptor,
        new java.lang.String[] { "Table", "Startkey", "Recordcount", "Field", });
    internal_static_ycsbleveldb_UpdateM_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_ycsbleveldb_UpdateM_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ycsbleveldb_UpdateM_descriptor,
        new java.lang.String[] { "Table", "Key", "Values", });
    internal_static_ycsbleveldb_InsertM_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_ycsbleveldb_InsertM_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ycsbleveldb_InsertM_descriptor,
        new java.lang.String[] { "Table", "Key", "Values", });
    internal_static_ycsbleveldb_DeleteM_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_ycsbleveldb_DeleteM_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ycsbleveldb_DeleteM_descriptor,
        new java.lang.String[] { "Table", "Key", });
    internal_static_ycsbleveldb_Result_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_ycsbleveldb_Result_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ycsbleveldb_Result_descriptor,
        new java.lang.String[] { "Result", });
    internal_static_ycsbleveldb_ReadResult_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_ycsbleveldb_ReadResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ycsbleveldb_ReadResult_descriptor,
        new java.lang.String[] { "Output", "Result", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
