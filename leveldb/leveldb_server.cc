#include <iostream>
#include <string>
#include <grpc/grpc.h>
#include <grpc++/server.h>
#include <grpc++/server_builder.h>
#include <grpc++/security/server_credentials.h>
#include "leveldb_ycsb.grpc.pb.h"

using grpc::Server;
using grpc::ServerBuilder;
using grpc::ServerContext;
using grpc::Status;
using ycsbleveldb::LevelDB;
using ycsbleveldb::ReadM;
using ycsbleveldb::ScanM;
using ycsbleveldb::UpdateM;
using ycsbleveldb::InsertM;
using ycsbleveldb::DeleteM;
using ycsbleveldb::Result;

class LevelDBServiceImpl final : public LevelDB::Service {
	Status Read(ServerContext* context, const ReadM* request, Result* reply) override {
		std::cout<<"???"<<std::endl;
		reply->set_result(123);
		return Status::OK;
	}
	Status Scan(ServerContext* context, const ScanM* request, Result* reply) override {
		reply->set_result(123);
		return Status::OK;
	}
	Status Update(ServerContext* context, const UpdateM* request, Result* reply) override {
		reply->set_result(123);
		return Status::OK;
	}
	Status Insert(ServerContext* context, const InsertM* request, Result* reply) override {
		reply->set_result(123);
		return Status::OK;
	}
	Status Ldelete(ServerContext* context, const DeleteM* request, Result* reply) override {
		reply->set_result(123);
		return Status::OK;
	}
};

void RunServer() {
	std::string server_address("0.0.0.0:30030");
	LevelDBServiceImpl service;

	ServerBuilder builder;
	builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
	builder.RegisterService(&service);
	std::unique_ptr<Server> server(builder.BuildAndStart());
	std::cout << "Server listening on " << server_address << std::endl;

	server->Wait();
}

int main(int argc, char** argv) {
	RunServer();
	return 0;
}
