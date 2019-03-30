//package com.gemini.cloud.sevice.test.hbase;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.apache.hadoop.hbase.client.HBaseAdmin;
//import org.apache.hadoop.hbase.client.Table;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//
//
//public class HbaseTest {
//
//    static Configuration config = null;
//    private Connection connection = null;
//    private Table table = null;
//
//
//    @Before
//    public void init() throws IOException {
//        config = HBaseConfiguration.create();
//        config.set("hbase.zookeeper.quorum", "127.0.0.1");// zookeeper地址
//        config.set("hbase.zookeeper.property.clientPort", "2181");// zookeeper端口
//        connection = ConnectionFactory.createConnection(config);
//        table = connection.getTable(TableName.valueOf("dept"));
//    }
//
//
//    @Test
//    public void createTable() throws IOException {
//
//        HBaseAdmin hBaseAdmin = new HBaseAdmin(config);
//
//
//    }
//
//}
