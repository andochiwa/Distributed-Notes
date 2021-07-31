package com.github;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author HAN
 * @version 1.0
 * @since 07-31-17:39
 */
public class DistributedLockTest {

    @Test
    void distributedLockTest() throws IOException, InterruptedException {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                DistributedLock lock = new DistributedLock();
                System.out.println("====线程" + finalI + "准备获得锁====");
                lock.zkLock();
                try {
                    System.out.println("====线程" + finalI + "获取锁====");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("====线程" + finalI + "释放锁====");
                    lock.zkUnlock();
                }
            }).start();
        }
        Thread.sleep(500);
        for (int i = 5; i < 7; i++) {
            int finalI = i;
            new Thread(() -> {
                DistributedLock lock = new DistributedLock();
                System.out.println("====线程" + finalI + "准备获得锁====");
                lock.zkLock();
                try {
                    System.out.println("====线程" + finalI + "获取锁====");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("====线程" + finalI + "释放锁====");
                    lock.zkUnlock();
                }
            }).start();
        }
        System.in.read();
    }
}
