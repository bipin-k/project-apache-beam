## Executions

```shell
mvn clean package exec:java -Dexec.mainClass=com.github.TestRunnerApp -Dexec.args="--runner=FlinkRunner --flinkMaster=localhost:8081 --filesToStage=target/project-apache-beam-bundled-1.0-SNAPSHOT.jar" -Pflink-runner
```

### Kafka 
```shell
./bin/zookeeper-server-start.sh config/zookeeper.properties

./bin/kafka-server-start.sh config/server.properties

./bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic hello_world --create --partitions 3 --replication-factor 1

./bin/kafka-topics.sh --bootstrap-server localhost:9092 --list

./bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic hello_world

./bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic hello_world

./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic output_topic --from-beginning
```
