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

3. The output will be in the folder results/ containing files with the listing of start time and duration for each transaction type (output.raw), the throughput and different latency measures in milliseconds (output.res)

### TPC-H
1. Create tables and relations between them

	```
	psql -U test -d test -f db_schemas/tpch-schema.sql
	(or psql -U test -d test -f db_schemas/tpch-schema_encrypted.sql)
	psql -U test -d test -f db_schemas/tpch-index.sql
	```

2. Generate tables

	```
	tool/dbgen -s 2
	```

3.  Run experiments

	```
	java -Dlog4j.configuration=log4j.properties -jar bin/tpch.jar -b tpch -o output -s 10 --config config/tpch_config.xml --load true --execute true
	```

4. The output will be in the folder results/ containing files with the listing of start time and duration for each transaction type (output.csv), the throughput and different latency measures in milliseconds (output.res)

