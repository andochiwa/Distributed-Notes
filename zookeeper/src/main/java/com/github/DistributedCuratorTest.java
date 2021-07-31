package com.github;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author HAN
 * @version 1.0
 * @since 07-31-18:44
 */
public class DistributedCuratorTest {

    @Test
    void distributedLockTest() throws IOException, InterruptedException {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                // 获取客户端
                CuratorFramework client = getCuratorClient();
                // 创建分布式锁
                InterProcessMutex lock = new InterProcessMutex(client, "/locks");
                System.out.println("====线程" + finalI + "准备获得锁====");
                try {
                    lock.acquire();
                    System.out.println("====线程" + finalI + "获取锁====");
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("====线程" + finalI + "释放锁====");
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(500);
        for (int i = 5; i < 7; i++) {
            int finalI = i;
            new Thread(() -> {
                // 获取客户端
                CuratorFramework client = getCuratorClient();
                // 创建分布式锁
                InterProcessMutex lock = new InterProcessMutex(client, "/locks");
                System.out.println("====线程" + finalI + "准备获得锁====");
                try {
                    lock.acquire();
                    System.out.println("====线程" + finalI + "获取锁====");
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("====线程" + finalI + "释放锁====");
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.in.read();
    }

    private CuratorFramework getCuratorClient() {
        // 获取curator客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181,localhost:2182,localhost:2183")
                .sessionTimeoutMs(2000)
                .retryPolicy(new ExponentialBackoffRetry(3000, 3))
                .build();
        client.start();
        return client;
    }
}
