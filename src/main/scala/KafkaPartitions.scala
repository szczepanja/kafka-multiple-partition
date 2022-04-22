import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

import java.time.Duration
import java.util.Properties

object KafkaPartitions extends App {

  val props = new Properties()
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "CG1")

  val consumer = new KafkaConsumer[String, String](props)

  import scala.jdk.CollectionConverters._

  consumer.subscribe(Seq("t1").asJava)

  while (true) {
    val records = consumer.poll(Duration.ofMillis(100)).asScala
    records.foreach { record =>
      System.out.printf("partition = %d, value = %s%n", record.partition, record.value)
    }
  }

}
