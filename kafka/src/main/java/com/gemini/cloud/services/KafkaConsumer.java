//package com.gemini.cloud.services;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import kafka.consumer.ConsumerConfig;
//import kafka.consumer.ConsumerIterator;
//import kafka.consumer.KafkaStream;
//import kafka.javaapi.consumer.ConsumerConnector;
//import kafka.serializer.StringDecoder;
//import kafka.utils.VerifiableProperties;
//
//public class KafkaConsumer {
//
//    private ConsumerConnector consumerConnector;
//
//
//    KafkaConsumer() {
//        Properties properties = new Properties();
//
//        properties.put("group.id", "test.consumer");
//        properties.put("zookeeper.connect", "127.0.0.1:2181");
//
//        // zk连接超时
//        properties.put("zookeeper.session.timeout.ms", "4000");
//        properties.put("zookeeper.sync.time.ms", "200");
//        properties.put("rebalance.max.retries", "5");
//        properties.put("rebalance.backoff.ms", "1200");
//
//
//        properties.put("auto.commit.interval.ms", "1000");
//        properties.put("auto.offset.reset", "smallest");
//        // 序列化类
//        properties.put("serializer.class", "kafka.serializer.StringEncoder");
//
//        ConsumerConfig config = new ConsumerConfig(properties);
//
//        consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(config);
//    }
//
//    private void kafkaConsume() {
//
//        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
//        topicCountMap.put(KafkaProducer.TOPIC, new Integer(1));
//
//        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
//        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
//
//        Map<String, List<KafkaStream<String, String>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
//        KafkaStream<String, String> stream = consumerMap.get(KafkaProducer.TOPIC).get(0);
//        ConsumerIterator<String, String> it = stream.iterator();
//        while (it.hasNext())
//            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + it.next().message() + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//    }
//
//    public static void main(String[] args) {
//        new KafkaConsumer().kafkaConsume();
//    }
//}
