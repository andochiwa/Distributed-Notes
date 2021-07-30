package com.github;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 03-13-21:54
 */
public class ConnectionTest {

    ZooKeeper zooKeeper;

    @BeforeEach
    void testConnection() throws IOException {

        // 访问ip和端口号，如果有多个则逗号隔开
        String connectionString = "localhost:2181,localhost:2182,localhost:2183";
        // 超时时间(ms)
        int sessionTimeout = 2000;

        zooKeeper = new ZooKeeper(connectionString, sessionTimeout,
                watchedEvent -> {
                    System.out.println("watchedEvent: " + watchedEvent);
                    try {
                        // 监听器
                        System.out.println("===============listener==============");
                        List<String> children = zooKeeper.getChildren("/", true);
                        children.forEach(System.out::println);
                    } catch (KeeperException | InterruptedException e) {
                        e.printStackTrace();
                    }

                });
        System.out.println(zooKeeper);
    }

    // 创建节点
    @Test
    void createNode() throws KeeperException, InterruptedException {
        // 路径，内容，模式，类型
        String path = zooKeeper.create("/hello", "world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println("=========created node==================");
        System.out.println(path);
    }

    // 获取子节点 并监控节点的变化
    @Test
    void getNode() throws KeeperException, InterruptedException {
        // 路径，是否监控，监听器在上面
        List<String> children = zooKeeper.getChildren("/", true);
        children.forEach(System.out::println);
    }

    // 判断节点是否存在
    @Test
    void nodeIsExist() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/hello", false);
        System.out.println("===========exists=============");
        System.out.println(exists);
    }

}
