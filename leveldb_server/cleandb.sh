#!/bin/sh

for i in PM
do
cp CMakeLists.txt.$i CMakeLists.txt
make -j 5

echo "run "$i
fname="server_log_"$i

sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sync

sudo rm -rf /mnt/ssd/thisisdb /mnt/mem0/db

sudo ./leveldb_server > $fname

sudo mv /mnt/ssd/thisisdb/LOG ~/LOG_3GDB_$((i))
done
