#include <iostream>
#include <cstdio>
#include <cstdint>
#include <string>
#include <sys/time.h>
#include <grpc/grpc.h>
#include <grpc++/server.h>
#include <grpc++/server_builder.h>
#include <grpc++/security/server_credentials.h>
#include "leveldb_ycsb.grpc.pb.h"

#include <typeinfo>

#include <leveldb/db.h>
#ifdef PMINDEXDB_BUILD
#include <leveldb/persistant_pool.h>
#endif

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
static FILE* ReadLog;
static FILE* WriteLog;
static FILE* UpdateLog;

uint64_t getMicrotime(){
	struct timeval currentTime;
	gettimeofday(&currentTime, NULL);
	return static_cast<uint64_t>(currentTime.tv_sec) * 1000000 + currentTime.tv_usec;
}

class LevelDBServiceImpl final : public LevelDB::Service {
	Status Read(ServerContext* context, const ReadM* request, ReadResult* reply) override {
		std::string key = request->key();
		std::string output = "";

		uint64_t start_time = getMicrotime();
		status = db->Get(leveldb::ReadOptions(), key, &output);
//		std::cout << "Read," << getMicrotime() - start_time << std::endl;

		if(false == status.ok()) {
			reply->set_result(1);
			reply->set_output("");
			return Status::OK;
		}
		reply->set_result(0);
		reply->set_output(output);
		return Status::OK;
	}
	Status Scan(ServerContext* context, const ScanM* request, ReadResult* reply) override {
		std::string key = request->startkey();
		int recordcount = request->recordcount();
		std::string output = "";
		uint64_t start_time = getMicrotime();
		leveldb::Iterator* it = db->NewIterator(leveldb::ReadOptions());
		int i = 0;
		for(it->Seek(key); it->Valid(); it->Next()) {
			if(i>=recordcount) break;
			output = output + it->value().ToString();
			i++;
		}
		std::cout << "Scan," << getMicrotime() - start_time << std::endl;

		reply->set_result(0);
		reply->set_output(output);
		return Status::OK;
	}
	Status Update(ServerContext* context, const UpdateM* request, Result* reply) override {
		std::string key = request->key();
		std::string value = request->values();

		leveldb::WriteOptions woptions;
		uint64_t start_time = getMicrotime();
		status = db->Put(woptions, key, value);
		std::cout << "Update," << getMicrotime() - start_time << std::endl;
		if(false == status.ok()) {
			reply->set_result(1);
			return Status::OK;
		}
		reply->set_result(0);
		return Status::OK;
	}
	Status Insert(ServerContext* context, const InsertM* request, Result* reply) override {
		std::string key = request->key();
		std::string value = request->values();
		
		leveldb::WriteOptions woptions;
		uint64_t start_time = getMicrotime();
		status = db->Put(woptions, key, value);
//		std::cout << "Insert," << getMicrotime() - start_time << std::endl;
		if(false == status.ok()) {
			reply->set_result(1);
			return Status::OK;
		}
		reply->set_result(0);
		return Status::OK;
	}
	Status Ldelete(ServerContext* context, const DeleteM* request, Result* reply) override {
		std::string key = request->key();
		
		uint64_t start_time = getMicrotime();
		status = db->Delete(leveldb::WriteOptions(), key);
		std::cout << "Delete," << getMicrotime() - start_time << std::endl;
		if(false == status.ok()) {
			reply->set_result(1);
			return Status::OK;
		}
		reply->set_result(0);
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
	ReadLog = fopen("ReadLog","w");
	if (NULL == ReadLog) perror("Error");
	WriteLog = fopen("WriteLog", "w");
	if (NULL == WriteLog) perror("Error");
	UpdateLog = fopen("UpdateLog", "w");
	if (NULL == UpdateLog) perror("Error");
#ifdef PMINDEXDB_BUILD
  std::string pmem_dir = "/mnt/mem0/db";
  size_t pmem_size = 4089446400;
  std::cout << pmem_size << std::endl;
  leveldb::nvram::create_pool(pmem_dir, pmem_size);
#endif
  	options.write_buffer_size=64*1024*1024; // memtable
	options.max_file_size=64*1024*1024; // sstable
	options.create_if_missing = true;
	status = leveldb::DB::Open(options, "/mnt/ssd/thisisdb", &db);
	if (!status.ok()) {
		std::cout << "[LevelDB] Open Fail" << std::endl;
		return 1;
	}

/*	leveldb::Iterator* it = db->NewIterator(leveldb::ReadOptions());
	int i = 0;
	for (it->SeekToFirst(); it->Valid(); it->Next()) {
		std::cout << it->key().ToString() << " : " << it->value().ToString() << std::endl;
		i++;
		if(i==10) break;
	}*/

	RunServer();
#ifdef PMINDEXDB_BUILD
	leveldb::nvram::close_pool();
#endif
	fclose(ReadLog);
	fclose(WriteLog);
	fclose(UpdateLog);
	return 0;
}
