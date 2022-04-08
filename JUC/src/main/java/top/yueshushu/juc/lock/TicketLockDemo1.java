package top.yueshushu.juc.lock;

/**
 * @ClassName:TicketLockDemo1
 * @Description 专票，用锁 Lock 进行处理
 * @Author zk_yjl
 * @Date 2022/3/24 14:09
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 不加同步时，  public void sale()  会卖出30张票， 但是卖出票的顺序 并不是从  30~1
 * 加同步时，  public synchronized void sale()   会卖出30张票，卖出票的顺序是从 30~1 依次递减的
 */
@Log4j
class Ticket {
    private int socketNum = 30;
    Lock lock = new ReentrantLock();

    public void sale() {
        /*
         注意，要将 lock.lock() 放置在 try 外边。
         当获取锁失败之后，就停止， 不会执行 finally 的unlock() 方法.

         如果将 lock.lock() 放置在 try 里面， 当获取锁失败之后，会执行 finally 的unlock() 方法。
         还没有加锁，就试图去解锁。 会出现问题。
        *
        * */

        lock.lock();
        try {
            if (socketNum > 0) {
                socketNum--;
                log.info(Thread.currentThread().getName() + ">>>>卖出第" + socketNum + "张票");
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            lock.unlock();
        }

    }
}

public class TicketLockDemo1 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(
                () -> {
                    for (int i = 0; i < 40; i++) {
                        ticket.sale();
                    }
                }
                , "线程A").start();

        new Thread(
                () -> {
                    for (int i = 0; i < 40; i++) {
                        ticket.sale();
                    }
                }
                , "线程B").start();

        new Thread(
                () -> {
                    for (int i = 0; i < 40; i++) {
                        ticket.sale();
                    }
                }
                , "线程C").start();
    }
}
