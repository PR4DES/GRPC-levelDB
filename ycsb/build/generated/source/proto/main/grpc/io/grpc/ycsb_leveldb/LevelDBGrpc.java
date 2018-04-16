package io.grpc.ycsb_leveldb;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.1)",
    comments = "Source: leveldb_ycsb.proto")
public class LevelDBGrpc {

  private LevelDBGrpc() {}

  public static final String SERVICE_NAME = "ycsbleveldb.LevelDB";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.ycsb_leveldb.ReadM,
      io.grpc.ycsb_leveldb.Result> METHOD_READ =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ycsbleveldb.LevelDB", "Read"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.ReadM.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.ycsb_leveldb.ScanM,
      io.grpc.ycsb_leveldb.Result> METHOD_SCAN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ycsbleveldb.LevelDB", "Scan"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.ScanM.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.ycsb_leveldb.UpdateM,
      io.grpc.ycsb_leveldb.Result> METHOD_UPDATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ycsbleveldb.LevelDB", "Update"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.UpdateM.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.ycsb_leveldb.InsertM,
      io.grpc.ycsb_leveldb.Result> METHOD_INSERT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ycsbleveldb.LevelDB", "Insert"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.InsertM.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.ycsb_leveldb.DeleteM,
      io.grpc.ycsb_leveldb.Result> METHOD_LDELETE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ycsbleveldb.LevelDB", "Ldelete"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.DeleteM.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.ycsb_leveldb.Result.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LevelDBStub newStub(io.grpc.Channel channel) {
    return new LevelDBStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LevelDBBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LevelDBBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static LevelDBFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LevelDBFutureStub(channel);
  }

  /**
   */
  public static abstract class LevelDBImplBase implements io.grpc.BindableService {

    /**
     */
    public void read(io.grpc.ycsb_leveldb.ReadM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_READ, responseObserver);
    }

    /**
     */
    public void scan(io.grpc.ycsb_leveldb.ScanM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SCAN, responseObserver);
    }

    /**
     */
    public void update(io.grpc.ycsb_leveldb.UpdateM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE, responseObserver);
    }

    /**
     */
    public void insert(io.grpc.ycsb_leveldb.InsertM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_INSERT, responseObserver);
    }

    /**
     */
    public void ldelete(io.grpc.ycsb_leveldb.DeleteM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LDELETE, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_READ,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.ycsb_leveldb.ReadM,
                io.grpc.ycsb_leveldb.Result>(
                  this, METHODID_READ)))
          .addMethod(
            METHOD_SCAN,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.ycsb_leveldb.ScanM,
                io.grpc.ycsb_leveldb.Result>(
                  this, METHODID_SCAN)))
          .addMethod(
            METHOD_UPDATE,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.ycsb_leveldb.UpdateM,
                io.grpc.ycsb_leveldb.Result>(
                  this, METHODID_UPDATE)))
          .addMethod(
            METHOD_INSERT,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.ycsb_leveldb.InsertM,
                io.grpc.ycsb_leveldb.Result>(
                  this, METHODID_INSERT)))
          .addMethod(
            METHOD_LDELETE,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.ycsb_leveldb.DeleteM,
                io.grpc.ycsb_leveldb.Result>(
                  this, METHODID_LDELETE)))
          .build();
    }
  }

  /**
   */
  public static final class LevelDBStub extends io.grpc.stub.AbstractStub<LevelDBStub> {
    private LevelDBStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LevelDBStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LevelDBStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LevelDBStub(channel, callOptions);
    }

    /**
     */
    public void read(io.grpc.ycsb_leveldb.ReadM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_READ, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void scan(io.grpc.ycsb_leveldb.ScanM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SCAN, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(io.grpc.ycsb_leveldb.UpdateM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void insert(io.grpc.ycsb_leveldb.InsertM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_INSERT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ldelete(io.grpc.ycsb_leveldb.DeleteM request,
        io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LDELETE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LevelDBBlockingStub extends io.grpc.stub.AbstractStub<LevelDBBlockingStub> {
    private LevelDBBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LevelDBBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LevelDBBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LevelDBBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.ycsb_leveldb.Result read(io.grpc.ycsb_leveldb.ReadM request) {
      return blockingUnaryCall(
          getChannel(), METHOD_READ, getCallOptions(), request);
    }

    /**
     */
    public io.grpc.ycsb_leveldb.Result scan(io.grpc.ycsb_leveldb.ScanM request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SCAN, getCallOptions(), request);
    }

    /**
     */
    public io.grpc.ycsb_leveldb.Result update(io.grpc.ycsb_leveldb.UpdateM request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE, getCallOptions(), request);
    }

    /**
     */
    public io.grpc.ycsb_leveldb.Result insert(io.grpc.ycsb_leveldb.InsertM request) {
      return blockingUnaryCall(
          getChannel(), METHOD_INSERT, getCallOptions(), request);
    }

    /**
     */
    public io.grpc.ycsb_leveldb.Result ldelete(io.grpc.ycsb_leveldb.DeleteM request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LDELETE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LevelDBFutureStub extends io.grpc.stub.AbstractStub<LevelDBFutureStub> {
    private LevelDBFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LevelDBFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LevelDBFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LevelDBFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.ycsb_leveldb.Result> read(
        io.grpc.ycsb_leveldb.ReadM request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_READ, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.ycsb_leveldb.Result> scan(
        io.grpc.ycsb_leveldb.ScanM request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SCAN, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.ycsb_leveldb.Result> update(
        io.grpc.ycsb_leveldb.UpdateM request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.ycsb_leveldb.Result> insert(
        io.grpc.ycsb_leveldb.InsertM request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_INSERT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.ycsb_leveldb.Result> ldelete(
        io.grpc.ycsb_leveldb.DeleteM request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LDELETE, getCallOptions()), request);
    }
  }

  private static final int METHODID_READ = 0;
  private static final int METHODID_SCAN = 1;
  private static final int METHODID_UPDATE = 2;
  private static final int METHODID_INSERT = 3;
  private static final int METHODID_LDELETE = 4;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LevelDBImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(LevelDBImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_READ:
          serviceImpl.read((io.grpc.ycsb_leveldb.ReadM) request,
              (io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result>) responseObserver);
          break;
        case METHODID_SCAN:
          serviceImpl.scan((io.grpc.ycsb_leveldb.ScanM) request,
              (io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((io.grpc.ycsb_leveldb.UpdateM) request,
              (io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result>) responseObserver);
          break;
        case METHODID_INSERT:
          serviceImpl.insert((io.grpc.ycsb_leveldb.InsertM) request,
              (io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result>) responseObserver);
          break;
        case METHODID_LDELETE:
          serviceImpl.ldelete((io.grpc.ycsb_leveldb.DeleteM) request,
              (io.grpc.stub.StreamObserver<io.grpc.ycsb_leveldb.Result>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_READ,
        METHOD_SCAN,
        METHOD_UPDATE,
        METHOD_INSERT,
        METHOD_LDELETE);
  }

}
