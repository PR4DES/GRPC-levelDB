cmake_minimum_required(VERSION 3.0)
project(DB-server CXX)

add_compile_options(
        -Wno-sign-compare
        -O2
        -DNDEBUG
)

set(PMINDEXDB_BUILD TRUE)

add_subdirectory(grpc)

if (LEVELDB_BUILD)
    add_subdirectory(leveldb)
    add_definitions(-DLEVELDB_BUILD=1)
endif(LEVELDB_BUILD)
if (PMINDEXDB_BUILD)
    add_subdirectory(PMIndexDB)
    add_definitions(-DPMINDEXDB_BUILD=1)
endif(PMINDEXDB_BUILD)

set(_PROTOBUF_LIBPROTOBUF libprotobuf)
set(_PROTOBUF_PROTOC $<TARGET_FILE:protoc>)
set(_GRPC_GRPCPP_UNSECURE grpc++_unsecure)
set(_GRPC_CPP_PLUGIN_EXECUTABLE $<TARGET_FILE:grpc_cpp_plugin>)

# Proto file
get_filename_component(hw_proto "../protos/leveldb_ycsb.proto" ABSOLUTE)
get_filename_component(hw_proto_path "${hw_proto}" PATH)

# Generated sources
set(hw_proto_srcs "${CMAKE_CURRENT_BINARY_DIR}/leveldb_ycsb.pb.cc")
set(hw_proto_hdrs "${CMAKE_CURRENT_BINARY_DIR}/leveldb_ycsb.pb.h")
set(hw_grpc_srcs "${CMAKE_CURRENT_BINARY_DIR}/leveldb_ycsb.grpc.pb.cc")
set(hw_grpc_hdrs "${CMAKE_CURRENT_BINARY_DIR}/leveldb_ycsb.grpc.pb.h")

add_custom_command(
        OUTPUT "${hw_proto_srcs}" "${hw_proto_hdrs}" "${hw_grpc_srcs}" "${hw_grpc_hdrs}"
        COMMAND ${_PROTOBUF_PROTOC}
        ARGS --grpc_out "${CMAKE_CURRENT_BINARY_DIR}"
        --cpp_out "${CMAKE_CURRENT_BINARY_DIR}"
        -I "${hw_proto_path}"
        --plugin=protoc-gen-grpc="${_GRPC_CPP_PLUGIN_EXECUTABLE}"
        "${hw_proto}"
        DEPENDS "${hw_proto}")

include_directories("${CMAKE_CURRENT_BINARY_DIR}")

add_executable(leveldb_server leveldb_server.cc histogram.cc perf_log2.cc
        ${hw_proto_srcs}
        ${hw_grpc_srcs})
target_link_libraries(leveldb_server
        ${_GRPC_GRPCPP_UNSECURE}
        ${_PROTOBUF_LIBPROTOBUF}
        leveldb)
