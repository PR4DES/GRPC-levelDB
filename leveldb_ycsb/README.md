build
```
$ ./gradlew installDist
```

IN YCSB

build database binding
```
$ mvn -pl com.yahoo.ycsb:leveldb-binding -am clean package
```

run ycsb
```
$ bin/ycsb.sh load leveldb -P workloads/workloada
$ bin/ycsb.sh run leveldb -P workloads/workloada
```

java file is here : src/main/java/io/grpc/ycsb_leveldb/YCSBLevelDBClient.java
