#include <iostream>
#include <string>
#include <grpc/grpc.h>
#include <grpc++/server.h>
#include <grpc++/server_builder.h>
#include <grpc++/security/server_credentials.h>
#include "leveldb_ycsb.grpc.pb.h"

#include <typeinfo>

#include <leveldb/db.h>

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
using ycsbleveldb::ReadResult;

leveldb::DB *db;
leveldb::Options options;
leveldb::Status status;

class LevelDBServiceImpl final : public LevelDB::Service {
	Status Read(ServerContext* context, const ReadM* request, ReadResult* reply) override {
		std::string key = request->key();
		std::string output = "";
		leveldb::Iterator* it = db->NewIterator(leveldb::ReadOptions());
		for(it->SeekToFirst(); it->Valid(); it->Next()) {
			if(it->key().ToString() == key) {
				output = output + it->value().ToString();
				break;
			}
		}
		reply->set_result(0);
		reply->set_output(output);
		return Status::OK;
	}
	Status Scan(ServerContext* context, const ScanM* request, Result* reply) override {
		reply->set_result(0);
		return Status::OK;
	}
	Status Update(ServerContext* context, const UpdateM* request, Result* reply) override {
		std::string key = request->key();
		std::string value = request->values();

		leveldb::WriteOptions woptions;
		db->Put(woptions, key, value);
		reply->set_result(0);
		return Status::OK;
	}
	Status Insert(ServerContext* context, const InsertM* request, Result* reply) override {
		std::string key = request->key();
		std::string value = request->values();
		
		leveldb::WriteOptions woptions;
		db->Put(woptions, key, value);
		reply->set_result(0);
		return Status::OK;
	}
	Status Ldelete(ServerContext* context, const DeleteM* request, Result* reply) override {
		std::string key = request->key();
		
		db->Delete(leveldb::WriteOptions(), key);
		reply->set_result(1);
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
	options.create_if_missing = true;

	status = leveldb::DB::Open(options, "../thisisdb", &db);
	if (false == status.ok()) {
		std::cout << "[LevelDB] Open Fail" << std::endl;
		return 1;
	}

	RunServer();
	return 0;
}
