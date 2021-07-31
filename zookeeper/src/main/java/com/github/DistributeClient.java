package com.github;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @since 07-31-16:43
 */
public class DistributeClient {

    String connectionPath = "localhost:2181,localhost:2182,localhost:2183";

    ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeClient client = new DistributeClient();
        // 获取连接
        ZooKeeper conn = client.getConn();
        client.zooKeeper = conn;

        // 监听/servers子节点的增加和删除
        client.getServerList(conn);

        // 启动业务逻辑
        client.business();
    }

    private void getServerList(ZooKeeper conn) throws KeeperException, InterruptedException {
        List<String> children = conn.getChildren("/servers", true);

        ArrayList<String> serversData = new ArrayList<>();

        children.forEach(node -> {
            try {
                byte[] data = conn.getData("/servers/" + node, false, null);

                serversData.add(new String(data));
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(serversData);
    }

    private void business() throws IOException {
        System.in.read();
    }


    private ZooKeeper getConn() throws IOException {
        return new ZooKeeper(connectionPath, 2000, watchedEvent -> {
            try {
                getServerList(zooKeeper);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
