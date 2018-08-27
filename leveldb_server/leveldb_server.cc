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

#include "perf_log.h"
#include <typeinfo>

#include <leveldb/db.h>
//#include <leveldb/util/perf_log.h>
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

int count;
int count2;

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
			std::cout << GetHistogram() << std::endl;
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
					uint64_t start_time = NowMicros();
					status = db->Put(woptions, key, value);
					uint64_t micros = NowMicros()- start_time;
					LogMicros(INSERT, micros);

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

					uint64_t start_time = getMicrotime();
					status = db->Get(leveldb::ReadOptions(), key, &output);
					uint64_t micros = NowMicros()- start_time;
					LogMicros(READ, micros);

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

					uint64_t start_time = getMicrotime();
					leveldb::Iterator* it = db->NewIterator(leveldb::ReadOptions());
					int i = 0;
					for(it->Seek(key); it->Valid(); it->Next()) {
						if(i>=recordcount) break;
						output.append(it->value().ToString());
						i++;
					}
					uint64_t micros = NowMicros()- start_time;
					LogMicros(SCAN, micros);

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

					leveldb::WriteOptions woptions;
					uint64_t start_time = getMicrotime();
					status = db->Put(woptions, key, value);
					uint64_t micros = NowMicros()- start_time;
					LogMicros(UPDATE, micros);
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
						count2++;
						if(count>3) {
							std::cout << "----" << std::endl;
							std::cout << GetInfo() << std::endl;
							ClearLogs();
							count = 0;
							if(count2>4) {
								exit(0);
							}
						}
						reply_.set_result(0);
					} else {

					uint64_t start_time = getMicrotime();
					status = db->Delete(leveldb::WriteOptions(), key);
					uint64_t micros = NowMicros()- start_time;
					LogMicros(DELETE, micros);
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
	leveldb::Iterator* it = db->NewIterator(leveldb::ReadOptions());
	int i = 0;
	for (it->SeekToFirst(); it->Valid(); it->Next()) {
		std::cout << it->key().ToString() << " : " << it->value().ToString() << std::endl;
		i++;
		if(i==10) break;
	}
}


int main(int argc, char** argv) {
	CreatePerfLog();
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
	ClosePerfLog();
	return 0;
}
