## How to start

0. Create the user and the database

	```
	psql
	postgres> CREATE USER test WITH PASSWORD 'password';
	postgres> CREATE DATABASE test;
	postgres> \c test;
	postgres> CREATE EXTENSION encdb;
	```

### TPC-C
1. Create tables and relations

	```
	psql -U test -d test -f db_schemas/tpcc-schema.sql(原benchmark测试)
	(or psql -U test -d test -f db_schemas/tpcc-schema_encrypted_foredb.sql)(用于我们的pg benchmark测试schema)
	```

2.  Run experiments

	```
	./oltpbench/oltpbenchmark -b tpcc -o output -s 10 --config config/tpcc_config.xml --load true --execute true
	```
这里 -s 10是和stealthdb一样的参数不要动，然后关于stealthdb里面各个scale的改动在config/tpcc_config.xml这里面有个scale factor可以修改。
--load是把benchmark数据load到数据库中，--execute 是开始执行benchmark，它俩可以分开执行.
3. The output will be in the folder results/ containing files with the listing of start time and duration for each transaction type (output.raw), the throughput and different latency measures in milliseconds (output.res)
