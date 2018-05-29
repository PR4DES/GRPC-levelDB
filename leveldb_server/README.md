1. Install GRPC
- pre-requisites
```
$ sudo apt-get install build-essential autoconf libtool pkg-config
$ sudo apt-get install libgflags-dev libgtest-dev
$ sudo apt-get install clang libc++-dev
```
- install
```
$ git clone -b $(curl -L https://grpc.io/release) https://github.com/grpc/grpc
$ cd grpc
$ git submodule update --init
$ make
$ sudo make install -C third_party/protobuf
$ sudo make install
```

2. Install leveldb
- pre-requisites
```
$ sudo apt-get install libsnappy-dev
```
and so on...

3. Run server (default port : 30030)
```
$ ./leveldb_server
```
