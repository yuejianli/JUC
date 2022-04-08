package top.yueshushu.juc.lock;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName:ReenLockDemo
 * @Description 可重入锁的演示
 * @Author zk_yjl
 * @Date 2022/3/24 14:48
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j
public class ReenLockDemo {
    public static void main(String[] args) {

      //  syncReenLock();

        reenLock();


    }

    private static void reenLock() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try{
            log.info(">>>>获取第一把锁");
            lock.lock();
            try{
                log.info(">>>>获取第二把锁");
                lock.lock();
                try{
                    log.info(">>>>获取第三把锁");
                }catch (Exception e){

                }finally {
                    lock.lock();;
                }
            }catch (Exception e){

            }finally {
                lock.lock();;
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    private static void syncReenLock() {
        Object lock = new Object();
        synchronized (lock){
            log.info(">>>>获取第一把锁");
            synchronized (lock){
                log.info(">>>获取第二把锁");
                synchronized (lock){
                    log.info(">>>获取第三把锁");
                }
            }
        }
    }
}
