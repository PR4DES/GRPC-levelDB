// Generated by the gRPC C++ plugin.
// If you make any local change, they will be lost.
// source: leveldb_ycsb.proto

#include "leveldb_ycsb.pb.h"
#include "leveldb_ycsb.grpc.pb.h"

#include <grpcpp/impl/codegen/async_stream.h>
#include <grpcpp/impl/codegen/async_unary_call.h>
#include <grpcpp/impl/codegen/channel_interface.h>
#include <grpcpp/impl/codegen/client_unary_call.h>
#include <grpcpp/impl/codegen/method_handler_impl.h>
#include <grpcpp/impl/codegen/rpc_service_method.h>
#include <grpcpp/impl/codegen/service_type.h>
#include <grpcpp/impl/codegen/sync_stream.h>
namespace ycsbleveldb {

static const char* LevelDB_method_names[] = {
  "/ycsbleveldb.LevelDB/Read",
  "/ycsbleveldb.LevelDB/Scan",
  "/ycsbleveldb.LevelDB/Update",
  "/ycsbleveldb.LevelDB/Insert",
  "/ycsbleveldb.LevelDB/Ldelete",
};

std::unique_ptr< LevelDB::Stub> LevelDB::NewStub(const std::shared_ptr< ::grpc::ChannelInterface>& channel, const ::grpc::StubOptions& options) {
  (void)options;
  std::unique_ptr< LevelDB::Stub> stub(new LevelDB::Stub(channel));
  return stub;
}

LevelDB::Stub::Stub(const std::shared_ptr< ::grpc::ChannelInterface>& channel)
  : channel_(channel), rpcmethod_Read_(LevelDB_method_names[0], ::grpc::internal::RpcMethod::NORMAL_RPC, channel)
  , rpcmethod_Scan_(LevelDB_method_names[1], ::grpc::internal::RpcMethod::NORMAL_RPC, channel)
  , rpcmethod_Update_(LevelDB_method_names[2], ::grpc::internal::RpcMethod::NORMAL_RPC, channel)
  , rpcmethod_Insert_(LevelDB_method_names[3], ::grpc::internal::RpcMethod::NORMAL_RPC, channel)
  , rpcmethod_Ldelete_(LevelDB_method_names[4], ::grpc::internal::RpcMethod::NORMAL_RPC, channel)
  {}

::grpc::Status LevelDB::Stub::Read(::grpc::ClientContext* context, const ::ycsbleveldb::ReadM& request, ::ycsbleveldb::ReadResult* response) {
  return ::grpc::internal::BlockingUnaryCall(channel_.get(), rpcmethod_Read_, context, request, response);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::ReadResult>* LevelDB::Stub::AsyncReadRaw(::grpc::ClientContext* context, const ::ycsbleveldb::ReadM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::ReadResult>::Create(channel_.get(), cq, rpcmethod_Read_, context, request, true);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::ReadResult>* LevelDB::Stub::PrepareAsyncReadRaw(::grpc::ClientContext* context, const ::ycsbleveldb::ReadM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::ReadResult>::Create(channel_.get(), cq, rpcmethod_Read_, context, request, false);
}

::grpc::Status LevelDB::Stub::Scan(::grpc::ClientContext* context, const ::ycsbleveldb::ScanM& request, ::ycsbleveldb::Result* response) {
  return ::grpc::internal::BlockingUnaryCall(channel_.get(), rpcmethod_Scan_, context, request, response);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::Result>* LevelDB::Stub::AsyncScanRaw(::grpc::ClientContext* context, const ::ycsbleveldb::ScanM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::Result>::Create(channel_.get(), cq, rpcmethod_Scan_, context, request, true);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::Result>* LevelDB::Stub::PrepareAsyncScanRaw(::grpc::ClientContext* context, const ::ycsbleveldb::ScanM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::Result>::Create(channel_.get(), cq, rpcmethod_Scan_, context, request, false);
}

::grpc::Status LevelDB::Stub::Update(::grpc::ClientContext* context, const ::ycsbleveldb::UpdateM& request, ::ycsbleveldb::Result* response) {
  return ::grpc::internal::BlockingUnaryCall(channel_.get(), rpcmethod_Update_, context, request, response);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::Result>* LevelDB::Stub::AsyncUpdateRaw(::grpc::ClientContext* context, const ::ycsbleveldb::UpdateM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::Result>::Create(channel_.get(), cq, rpcmethod_Update_, context, request, true);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::Result>* LevelDB::Stub::PrepareAsyncUpdateRaw(::grpc::ClientContext* context, const ::ycsbleveldb::UpdateM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::Result>::Create(channel_.get(), cq, rpcmethod_Update_, context, request, false);
}

::grpc::Status LevelDB::Stub::Insert(::grpc::ClientContext* context, const ::ycsbleveldb::InsertM& request, ::ycsbleveldb::Result* response) {
  return ::grpc::internal::BlockingUnaryCall(channel_.get(), rpcmethod_Insert_, context, request, response);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::Result>* LevelDB::Stub::AsyncInsertRaw(::grpc::ClientContext* context, const ::ycsbleveldb::InsertM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::Result>::Create(channel_.get(), cq, rpcmethod_Insert_, context, request, true);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::Result>* LevelDB::Stub::PrepareAsyncInsertRaw(::grpc::ClientContext* context, const ::ycsbleveldb::InsertM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::Result>::Create(channel_.get(), cq, rpcmethod_Insert_, context, request, false);
}

::grpc::Status LevelDB::Stub::Ldelete(::grpc::ClientContext* context, const ::ycsbleveldb::DeleteM& request, ::ycsbleveldb::Result* response) {
  return ::grpc::internal::BlockingUnaryCall(channel_.get(), rpcmethod_Ldelete_, context, request, response);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::Result>* LevelDB::Stub::AsyncLdeleteRaw(::grpc::ClientContext* context, const ::ycsbleveldb::DeleteM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::Result>::Create(channel_.get(), cq, rpcmethod_Ldelete_, context, request, true);
}

::grpc::ClientAsyncResponseReader< ::ycsbleveldb::Result>* LevelDB::Stub::PrepareAsyncLdeleteRaw(::grpc::ClientContext* context, const ::ycsbleveldb::DeleteM& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncResponseReaderFactory< ::ycsbleveldb::Result>::Create(channel_.get(), cq, rpcmethod_Ldelete_, context, request, false);
}

LevelDB::Service::Service() {
  AddMethod(new ::grpc::internal::RpcServiceMethod(
      LevelDB_method_names[0],
      ::grpc::internal::RpcMethod::NORMAL_RPC,
      new ::grpc::internal::RpcMethodHandler< LevelDB::Service, ::ycsbleveldb::ReadM, ::ycsbleveldb::ReadResult>(
          std::mem_fn(&LevelDB::Service::Read), this)));
  AddMethod(new ::grpc::internal::RpcServiceMethod(
      LevelDB_method_names[1],
      ::grpc::internal::RpcMethod::NORMAL_RPC,
      new ::grpc::internal::RpcMethodHandler< LevelDB::Service, ::ycsbleveldb::ScanM, ::ycsbleveldb::Result>(
          std::mem_fn(&LevelDB::Service::Scan), this)));
  AddMethod(new ::grpc::internal::RpcServiceMethod(
      LevelDB_method_names[2],
      ::grpc::internal::RpcMethod::NORMAL_RPC,
      new ::grpc::internal::RpcMethodHandler< LevelDB::Service, ::ycsbleveldb::UpdateM, ::ycsbleveldb::Result>(
          std::mem_fn(&LevelDB::Service::Update), this)));
  AddMethod(new ::grpc::internal::RpcServiceMethod(
      LevelDB_method_names[3],
      ::grpc::internal::RpcMethod::NORMAL_RPC,
      new ::grpc::internal::RpcMethodHandler< LevelDB::Service, ::ycsbleveldb::InsertM, ::ycsbleveldb::Result>(
          std::mem_fn(&LevelDB::Service::Insert), this)));
  AddMethod(new ::grpc::internal::RpcServiceMethod(
      LevelDB_method_names[4],
      ::grpc::internal::RpcMethod::NORMAL_RPC,
      new ::grpc::internal::RpcMethodHandler< LevelDB::Service, ::ycsbleveldb::DeleteM, ::ycsbleveldb::Result>(
          std::mem_fn(&LevelDB::Service::Ldelete), this)));
}

LevelDB::Service::~Service() {
}

::grpc::Status LevelDB::Service::Read(::grpc::ServerContext* context, const ::ycsbleveldb::ReadM* request, ::ycsbleveldb::ReadResult* response) {
  (void) context;
  (void) request;
  (void) response;
  return ::grpc::Status(::grpc::StatusCode::UNIMPLEMENTED, "");
}

::grpc::Status LevelDB::Service::Scan(::grpc::ServerContext* context, const ::ycsbleveldb::ScanM* request, ::ycsbleveldb::Result* response) {
  (void) context;
  (void) request;
  (void) response;
  return ::grpc::Status(::grpc::StatusCode::UNIMPLEMENTED, "");
}

::grpc::Status LevelDB::Service::Update(::grpc::ServerContext* context, const ::ycsbleveldb::UpdateM* request, ::ycsbleveldb::Result* response) {
  (void) context;
  (void) request;
  (void) response;
  return ::grpc::Status(::grpc::StatusCode::UNIMPLEMENTED, "");
}

::grpc::Status LevelDB::Service::Insert(::grpc::ServerContext* context, const ::ycsbleveldb::InsertM* request, ::ycsbleveldb::Result* response) {
  (void) context;
  (void) request;
  (void) response;
  return ::grpc::Status(::grpc::StatusCode::UNIMPLEMENTED, "");
}

::grpc::Status LevelDB::Service::Ldelete(::grpc::ServerContext* context, const ::ycsbleveldb::DeleteM* request, ::ycsbleveldb::Result* response) {
  (void) context;
  (void) request;
  (void) response;
  return ::grpc::Status(::grpc::StatusCode::UNIMPLEMENTED, "");
}


}  // namespace ycsbleveldb

