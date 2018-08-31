#include <iostream>
#include <cstdio>
#include <cstdint>
#include <string>
#include <sys/time.h>
#include <unistd.h>
#include <grpc/grpc.h>
#include <grpc++/server.h>
#include <grpc++/server_builder.h>
#include <grpc++/security/server_credentials.h>
#include "leveldb_ycsb.grpc.pb.h"

#include "perf_log2.h"
#include <typeinfo>

#include <leveldb/db.h>
#include <leveldb/util/perf_log.h>
#ifdef PMINDEXDB_BUILD
#include <leveldb/persistant_pool.h>
#endif

using grpc::Server;
using grpc::ServerAsyncResponseWriter;
using grpc::ServerCompletionQueue;
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

int notfound=0;
int count;
int count2;
static int thread=4;

uint64_t getMicrotime(){
	struct timeval currentTime;
	gettimeofday(&currentTime, NULL);
	return static_cast<uint64_t>(currentTime.tv_sec) * 1000000 + currentTime.tv_usec;
}

class ServerImpl final {
	public:
		~ServerImpl() {
			server_->Shutdown();
			cq_->Shutdown();
		}
		void Run() {
			std::string server_address("0.0.0.0:30030");
			ServerBuilder builder;
			builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
			builder.RegisterService(&service_);
			cq_ = builder.AddCompletionQueue();
			server_ = builder.BuildAndStart();
			std::cout << "Server listening on " << server_address << std::endl;

			HandleRpcs();
		}

	private:
		class CallData {
			public:
				virtual void Proceed() = 0;
		};
		class InsertCallData final : public CallData{
			public:
				InsertCallData(LevelDB::AsyncService* service, grpc::ServerCompletionQueue* cq)
					: service_(service), cq_(cq), responder_(&ctx_), status_(CREATE) {
					Proceed();
					}
				void Proceed() {
					if (status_ == CREATE) {
						status_ = PROCESS;
						service_->RequestInsert(&ctx_, &request_, &responder_, cq_, cq_,
								this);
					} else if (status_ == PROCESS) {
					new InsertCallData(service_, cq_);

					std::string key = request_.key();
					std::string value = request_.values();
		
					leveldb::WriteOptions woptions;
					uint64_t start_time = NowMicros2();
					status = db->Put(woptions, key, value);
					uint64_t micros = NowMicros2()- start_time;
					LogMicros2(INSERT, micros);

					std::cout << "[INSERT]" << key << std::endl;

					if(false == status.ok()) {
						reply_.set_result(1);
					} else {
						reply_.set_result(0);
					}

					status_ = FINISH;
					responder_.Finish(reply_, Status::OK, this);
				} else {
					GPR_ASSERT(status_ == FINISH);
					delete this;
				}
			}
			private:
				LevelDB::AsyncService* service_;
				grpc::ServerCompletionQueue* cq_;
				ServerContext ctx_;
				
				InsertM request_;
				Result reply_;
			
				ServerAsyncResponseWriter<Result> responder_;
				enum CallStatus { CREATE, PROCESS, FINISH };
				CallStatus status_;
		};
		class ReadCallData final : public CallData {
			public:
				ReadCallData(LevelDB::AsyncService* service, grpc::ServerCompletionQueue* cq)
					: service_(service), cq_(cq), responder_(&ctx_), status_(CREATE) {
					Proceed();
					}
				void Proceed() {
					if (status_ == CREATE) {
						status_ = PROCESS;
						service_->RequestRead(&ctx_, &request_, &responder_, cq_, cq_,
								this);
					} else if (status_ == PROCESS) {
					new ReadCallData(service_, cq_);
					std::string key = request_.key();
					std::string output = "";

					uint64_t start_time = NowMicros2();
					status = db->Get(leveldb::ReadOptions(), key, &output);
					uint64_t micros = NowMicros2()- start_time;
					LogMicros2(READ, micros);
					if(output=="") notfound++;
					
					std::cout << "[READ]" << key << std::endl;

					if(false == status.ok()) {
						reply_.set_result(1);
						reply_.set_output("");
					} else {
						reply_.set_result(0);
						reply_.set_output(output);
					}

					status_ = FINISH;
					responder_.Finish(reply_, Status::OK, this);
					} else {
						GPR_ASSERT(status_ == FINISH);
						delete this;
					}
				}
			private:
				LevelDB::AsyncService* service_;
				grpc::ServerCompletionQueue* cq_;
				ServerContext ctx_;
				
				ReadM request_;
				ReadResult reply_;
			
				ServerAsyncResponseWriter<ReadResult> responder_;
				enum CallStatus { CREATE, PROCESS, FINISH };
				CallStatus status_;
		};
		class ScanCallData final : public CallData {
			public:
				ScanCallData(LevelDB::AsyncService* service, grpc::ServerCompletionQueue* cq)
					: service_(service), cq_(cq), responder_(&ctx_), status_(CREATE) {
					Proceed();
					}
				void Proceed() {
					if (status_ == CREATE) {
						status_ = PROCESS;
						service_->RequestScan(&ctx_, &request_, &responder_, cq_, cq_,
								this);
					} else if (status_ == PROCESS) {
					new ScanCallData(service_, cq_);
					std::string key = request_.startkey();
					int recordcount = request_.recordcount();
					std::string output = "";

					uint64_t start_time = NowMicros2();
					leveldb::Iterator* it = db->NewIterator(leveldb::ReadOptions());
					int i = 0;
					for(it->Seek(key); it->Valid(); it->Next()) {
						if(i>=recordcount) break;
						output.append(it->value().ToString());
						i++;
					}
					uint64_t micros = NowMicros2()- start_time;
					LogMicros2(SCAN, micros);

					reply_.set_result(0);
					reply_.set_output(output);

					status_ = FINISH;
					responder_.Finish(reply_, Status::OK, this);
				} else {
					GPR_ASSERT(status_ == FINISH);
					delete this;
				}
			}
			private:
				LevelDB::AsyncService* service_;
				grpc::ServerCompletionQueue* cq_;
				ServerContext ctx_;
				
				ScanM request_;
				ReadResult reply_;
			
				ServerAsyncResponseWriter<ReadResult> responder_;
				enum CallStatus { CREATE, PROCESS, FINISH };
				CallStatus status_;
		};
		class UpdateCallData final : public CallData {
			public:
				UpdateCallData(LevelDB::AsyncService* service, grpc::ServerCompletionQueue* cq)
					: service_(service), cq_(cq), responder_(&ctx_), status_(CREATE) {
					Proceed();
					}
				void Proceed() {
					if (status_ == CREATE) {
						status_ = PROCESS;
						service_->RequestUpdate(&ctx_, &request_, &responder_, cq_, cq_,
								this);
					} else if (status_ == PROCESS) {
					new UpdateCallData(service_, cq_);
					std::string key = request_.key();
					std::string value = request_.values();
					std::string output="";

					leveldb::WriteOptions woptions;
					uint64_t start_time = NowMicros2();
					status = db->Get(leveldb::ReadOptions(), key, &output);
					if(output != "") {
						status = db->Put(woptions, key, value);
						uint64_t micros = NowMicros2()- start_time;
					}
					LogMicros2(UPDATE, micros);
					if(output=="") {
						reply_.set_result(2);
					} else {	
						if(false == status.ok()) {
							reply_.set_result(1);
						} else {
							reply_.set_result(0);
						}
					}

					status_ = FINISH;
					responder_.Finish(reply_, Status::OK, this);
				} else {
					GPR_ASSERT(status_ == FINISH);
					delete this;
				}
			}
			private:
				LevelDB::AsyncService* service_;
				grpc::ServerCompletionQueue* cq_;
				ServerContext ctx_;
				
				UpdateM request_;
				Result reply_;
			
				ServerAsyncResponseWriter<Result> responder_;
				enum CallStatus { CREATE, PROCESS, FINISH };
				CallStatus status_;
		};
		class DeleteCallData final : public CallData {
			public:
				DeleteCallData(LevelDB::AsyncService* service, grpc::ServerCompletionQueue* cq)
					: service_(service), cq_(cq), responder_(&ctx_), status_(CREATE) {
					Proceed();
					}
				void Proceed() {
					if (status_ == CREATE) {
						status_ = PROCESS;
						service_->RequestLdelete(&ctx_, &request_, &responder_, cq_, cq_,
								this);
					} else if (status_ == PROCESS) {
					new DeleteCallData(service_, cq_);
					std::string key = request_.key();
					
					if(key=="") {
						count++;
						if(count>=thread) {
							std::cout << "----" << std::endl;
							std::cout << GetInfo2() << std::endl;
							std::cout << "----" << std::endl;
							std::cout << leveldb::benchmark::GetInfo() << std::endl;
							std::cout << "READ NOTFOUND " << notfound << std::endl;
							ClearLogs2();
							count = 0;
							notfound=0;
							count2++;
							if(count2>=3) {
								exit(0);
							}
						}
						reply_.set_result(0);
					} else {

					uint64_t start_time = NowMicros2();
					status = db->Delete(leveldb::WriteOptions(), key);
					uint64_t micros = NowMicros2()- start_time;
					LogMicros2(DELETE, micros);
					if(false == status.ok()) {
						reply_.set_result(1);
					} else {
						reply_.set_result(0);
					}
					}

					status_ = FINISH;
					responder_.Finish(reply_, Status::OK, this);
				} else {
					GPR_ASSERT(status_ == FINISH);
					delete this;
				}
			}
			private:
				LevelDB::AsyncService* service_;
				grpc::ServerCompletionQueue* cq_;
				ServerContext ctx_;
				
				DeleteM request_;
				Result reply_;
			
				ServerAsyncResponseWriter<Result> responder_;
				enum CallStatus { CREATE, PROCESS, FINISH };
				CallStatus status_;
		};

		void HandleRpcs() {
			new InsertCallData(&service_, cq_.get());
			new ReadCallData(&service_, cq_.get());
			new ScanCallData(&service_, cq_.get());
			new UpdateCallData(&service_, cq_.get());
			new DeleteCallData(&service_, cq_.get());
			void* tag;
			bool ok;
			while(true) {
				GPR_ASSERT(cq_->Next(&tag, &ok));
				GPR_ASSERT(ok);
				static_cast<CallData*>(tag)->Proceed();
			}
		}

		std::unique_ptr<grpc::ServerCompletionQueue> cq_;
		LevelDB::AsyncService service_;
		std::unique_ptr<Server> server_;
};

void Print10records() {
	std::cout << "[PRINT DB]" << std::endl;
	leveldb::Iterator* it = db->NewIterator(leveldb::ReadOptions());
	it->SeekToFirst();
	std::cout << "it->key() : " << it->key().ToString() << std::endl;
	int i = 0;
	for (it->SeekToFirst(); it->Valid(); it->Next()) {
		std::cout << it->key().ToString() << " : " << it->value().ToString() << std::endl;
	}
}


int main(int argc, char** argv) {
	leveldb::benchmark::CreatePerfLog();
	CreatePerfLog2();
	count=0;
	count2=0;
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

//	Print10records();

	ServerImpl server;
	server.Run();
#ifdef PMINDEXDB_BUILD
	leveldb::nvram::close_pool();
#endif
	ClosePerfLog2();
	leveldb::benchmark::ClosePerfLog();
	return 0;
}
