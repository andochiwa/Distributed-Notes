package com.github;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author HAN
 * @version 1.0
 * @since 07-31-17:39
 */
public class DistributedLock {

    private final String connectionPath = "localhost:2181,localhost:2182,localhost:2183";

    private ZooKeeper zooKeeper;

    private final CountDownLatch waitLock = new CountDownLatch(1);

    private String preNode;
    private String curNode;

    public DistributedLock() {
        try {
            zooKeeper = new ZooKeeper(connectionPath, 2000, event -> {
                // 因为前一个节点被删除了，所以暂时释放wait锁准备再次判断
                if (event.getType() == Watcher.Event.EventType.NodeDeleted && event.getPath().equals(preNode)) {
                    waitLock.countDown();
                }
            });

            // 判断根节点/locks是否存在
            Stat stat = zooKeeper.exists("/locks", false);

            if (stat == null) {
                synchronized (DistributedLock.class) {
                    stat = zooKeeper.exists("/locks", false);
                    if (stat == null) {
                        zooKeeper.create("/locks", "locks".getBytes(),
                                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    }
                }

            }
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    public void zkLock() {
        try {
            // 创建临时带序号节点

            this.curNode = zooKeeper.create("/locks/seq-", null,
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            // 截取后缀部分
            String curNode = this.curNode.substring("/locks/".length());

            // 判断当前节点是否为最小序号节点，如果是则获得锁，否则监听前一个节点
            // 先获取所有排序后的节点
            for (;;) {
                List<String> children = getSortChildren();
                if (children == null) {
                    throw new KeeperException.NoNodeException("节点不存在" + curNode);
                }
                int index = children.indexOf(curNode);
                if (index == -1) {
                    throw new KeeperException.NoNodeException("节点不存在" + curNode);
                } else if (index == 0) {
                    // 最前节点
                    return;
                }
                preNode = "/locks/" + children.get(index - 1);
                zooKeeper.getData(preNode, true, null);
                waitLock.await();
            }

        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<String> getSortChildren() {
        try {
            List<String> children = zooKeeper.getChildren("/locks", false);
            Collections.sort(children);
            return children;
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void zkUnlock() {
        // 删除节点
        try {
            zooKeeper.delete(curNode, -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
