#include <leveldb/db.h>
#include <iostream>

using namespace std;

int main() {

	leveldb::DB *db;
	leveldb::Options options;
	leveldb::Status status;

	options.create_if_missing = true;
	status = leveldb::DB::Open(options, "/mnt/ssd/thisisdb", &db);
	assert(status.ok());


	leveldb::Iterator* it = db->NewIterator(leveldb::ReadOptions());
	int i = 0;
	for (it->SeekToFirst(); it->Valid(); it->Next()) {
		cout << it->key().ToString() << " : " << it->value().ToString() << endl;
		i++;
		if(i==10) break;
	}

	delete db;
}

