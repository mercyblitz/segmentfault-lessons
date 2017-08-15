package com.segmentfault.springbootlesson11;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * 生产者 测试
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.09
 */
public class ProducerMain {


    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093");
        properties.put("key.serializer", StringSerializer.class);
        properties.put("value.serializer", StringSerializer.class);

        KafkaProducer kafkaProducer = new KafkaProducer(properties);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("sf-2", 0, "message", "大家好");

        Future<RecordMetadata> future = kafkaProducer.send(producerRecord);

        RecordMetadata recordMetadata = future.get();

        System.out.println(recordMetadata);

    }

}
