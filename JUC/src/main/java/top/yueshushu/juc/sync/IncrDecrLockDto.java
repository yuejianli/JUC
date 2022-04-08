package top.yueshushu.juc.sync;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName:IncrDecrLockDto
 * @Description 锁使用 Decr
 * @Author zk_yjl
 * @Date 2022/3/25 14:27
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j
public class IncrDecrLockDto {
    private int num = 0;
    //1. 定义一个锁
    private Lock lock = new ReentrantLock();
    //创建一个锁对应的条件
    private Condition condition = lock.newCondition();

    /**
     * 加1
     *
     * @param
     * @return void
     * @date 2022/3/25 14:30
     * @author zk_yjl
     */
    public void incr() {
        lock.lock();
        try {
            while (num != 0) {
                //等待
                condition.await();
            }
            num++;
            log.info(Thread.currentThread().getName() + "获取对象是" + num);
            //通知其他的
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 减1
     *
     * @param
     * @return void
     * @date 2022/3/25 14:30
     * @author zk_yjl
     */
    public void decr() {
        lock.lock();
        try {
            while (num != 1) {
                condition.await();
            }
            num--;
            log.info(Thread.currentThread().getName() + "获取对象是" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
