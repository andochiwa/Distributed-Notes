package com.github;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author HAN
 * @version 1.0
 * @create 03-13-21:54
 */
public class ConnectionTest {

    @Test
    void testConnection() throws IOException {

        // 访问ip和端口号，如果有多个则逗号隔开
        String connectionString = "localhost:2181";

        // 超时时间(ms)
        int sessionTimeout = 2000;

        ZooKeeper zooKeeper = new ZooKeeper(connectionString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

        System.out.println(zooKeeper);
    }

}
