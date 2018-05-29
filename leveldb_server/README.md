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
$ cd third_party/protobuf
$ sudo make install
$ cd ../../
$ git submodule update --init
$ make
$ sudo make install
```

2. Run server (default port : 30030)
```
$ ./leveldb_server
```
