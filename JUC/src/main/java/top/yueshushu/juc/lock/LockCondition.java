package top.yueshushu.juc.lock;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName:LockCondition
 * @Description 锁的条件配置
 * @Author zk_yjl
 * @Date 2022/3/25 14:48
 * @Version 1.0
 * @Since 1.0
 *
 * A线程打印 5 次A，B线程打印 10 次B，C线程打印 15 次 C,按照 此顺序循环 10 轮==
 **/
@Log4j
public class LockCondition {
    //定义锁
    private Lock lock = new ReentrantLock();
    // 定义锁的条件
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    //定义一个标识位，来决定应该打印谁
    private int flag = 1;

    //进行打印

    public void printA(int loop) {
        lock.lock();
        try {
            while (flag != 1) {
                conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                log.info(Thread.currentThread().getName() + "第" + loop + "打印信息:" + i);
            }
            flag = 2;
            //唤醒B 线程
            conditionB.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public void printB(int loop) {
        lock.lock();
        try {
            while (flag != 2) {
                conditionB.await();
            }
            //处理信息
            for (int i = 0; i < 10; i++) {
                log.info(Thread.currentThread().getName() + "第" + loop + "打印信息:" + i);
            }
            flag = 3;
            conditionC.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public void printC(int loop) {
        lock.lock();
        try {
            while (flag != 3) {
                conditionC.await();
            }
            //处理信息
            for (int i = 0; i < 15; i++) {
                log.info(Thread.currentThread().getName() + "第" + loop + "打印信息:" + i);
            }
            flag = 1;
            conditionA.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        LockCondition lockCondition = new LockCondition();

        new Thread(() -> {
              for(int i= 0; i<10;i++ ){
                  lockCondition.printA(i);
              }
        }, "线程A").start();

        new Thread(() -> {
            for(int i= 0; i<10;i++ ){
                lockCondition.printB(i);
            }
        }, "线程B").start();

        new Thread(() -> {
            for(int i= 0; i<10;i++ ){
                lockCondition.printC(i);
            }
        }, "线程C").start();
    }
}
