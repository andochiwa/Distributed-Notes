package com.github;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author HAN
 * @version 1.0
 * @since 07-31-16:33
 */
public class DistributeServer {
    String connectionPath = "localhost:2181,localhost:2182,localhost:2183";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeServer server = new DistributeServer();
        // 获取连接
        ZooKeeper conn = server.getConn();

        // 注册服务器到zk集群, 需要设置环境参数，或者自行取服务器的名字
        server.register(conn, args[0]);

        // 启动业务逻辑
        server.business();
    }

    private void business() throws IOException {
        System.in.read();
    }

    private void register(ZooKeeper conn, String hostname) throws KeeperException, InterruptedException {
        conn.create("/servers/" + hostname, hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(hostname + " is online");
    }

    private ZooKeeper getConn() throws IOException {
        return new ZooKeeper(connectionPath, 2000, watchedEvent -> {

        });
    }
}
