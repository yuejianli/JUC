package top.yueshushu.juc.sync;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName:DeadLockDemo1
 * @Description 编写一个死锁的程序
 * @Author zk_yjl
 * @Date 2022/3/24 13:59
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j
public class DeadLockDemo1 {
    public static void main(String[] args) {
        // 定义两个对象.
        Object zhangsan = new Object();

        Object lisi = new Object();
        /*
        * 死锁
        *
        * */
        //两个线程进行处理
        new Thread(
                () ->{
                    synchronized ( zhangsan ){
                        log.info(Thread.currentThread().getName()+"拥有获取到锁，正准备获取lisi的锁");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized ( lisi ){
                            log.info(Thread.currentThread().getName()+"拥有获取到 lisi的锁");
                        }
                    }
                }
        ,"张三").start();

        new Thread(
                () ->{
                    synchronized ( lisi ){
                        log.info(Thread.currentThread().getName()+"拥有获取到锁，正准备获取zhangsan 的锁");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized ( zhangsan ){
                            log.info(Thread.currentThread().getName()+"拥有获取到 zhangsan 的锁");
                        }
                    }
                }
                ,"李四").start();
    }
}
