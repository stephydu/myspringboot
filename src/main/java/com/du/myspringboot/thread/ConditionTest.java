package com.du.myspringboot.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        Condition c1 =lock.newCondition();
        Condition c2=lock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println("快乐");
            }
        }
        ).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("新年");
                try {
                    Thread.sleep(100);
                    c1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }

        ).start();

    }
}
