build (don't need to)
```
$ ./gradlew installDist
```

IN YCSB
```
$ git clone https://github.com/brianfrankcooper/YCSB
```
add line(s) in
pom.xml
<leveldb.version>1.2</leveldb.version>
<module>leveldb</module>

bin/bindings.properties
leveldb:com.yahoo.ycsb.db.leveldb.YCSBLevelDBClient

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
