package top.yueshushu.juc.threeauxiliary;/**
 * @ClassName:SemaphoreTest
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/3/31 17:30
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * acquire() 获得许可， release 释放许可
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class SemaphoreTest {
    // 定义信号灯
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {

        //定义6个车进行抢3个车位
        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> {
                        try {

                            if (semaphore.availablePermits() == 0) {
                                log.info(">>>>没有停车位了，请耐心等待");
                            }
                            semaphore.acquire();
                            log.info(Thread.currentThread().getName() + "获得车位");
                            int sleepTime = new Random().nextInt(5) + 1;
                            TimeUnit.SECONDS.sleep(sleepTime);
                            log.info(Thread.currentThread().getName() + "停" + sleepTime + "秒钟");
                            log.info(Thread.currentThread().getName() + "开走了车");
                            semaphore.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {

                        }
                    }
                    , i + "车").start();
        }
    }

}
