build (don't need to)
```
$ ./gradlew installDist
```

1. cp -r com ~/.m2/repository
2. check maven version is higher than 3.1.0
3. get YCSB
```
$ git clone https://github.com/brianfrankcooper/YCSB
```
4. add line(s) in
- pom.xml
<leveldb.version>1.2</leveldb.version>
<module>leveldb</module>
- bin/bindings.properties
leveldb:com.yahoo.ycsb.db.leveldb.YCSBLevelDBClient
5. build database binding
```
$ mvn -pl com.yahoo.ycsb:leveldb-binding -am clean package
```
* make parent version in leveldb/pom.xml be same as others
6. run ycsb
```
$ bin/ycsb.sh load leveldb -P workloads/workloada
$ bin/ycsb.sh run leveldb -P workloads/workloada
```

java file is here : src/main/java/io/grpc/ycsb_leveldb/YCSBLevelDBClient.java
