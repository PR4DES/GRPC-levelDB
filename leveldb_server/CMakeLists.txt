cmake_minimum_required(VERSION 3.10)
project(DB-server)

add_subdirectory(leveldb)
add_subdirectory(grpc)
set(_PROTOBUF_LIBPROTOBUF libprotobuf)
set(_PROTOBUF_PROTOC $<TARGET_FILE:protoc>)
set(_GRPC_GRPCPP_UNSECURE grpc++_unsecure)
set(_GRPC_CPP_PLUGIN_EXECUTABLE $<TARGET_FILE:grpc_cpp_plugin>)

add_executable(db_server leveldb_server.cc ${hw_proto_srcs} ${hw_grpc_srcs})
target_link_libraries(db_server leveldb grpc ${_GRPC_GRPCPP_UNSECURE} ${_PROTOBUF_LIBPROTOBUF})
