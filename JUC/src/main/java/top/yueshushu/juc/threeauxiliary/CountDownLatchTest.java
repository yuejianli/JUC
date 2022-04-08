package top.yueshushu.juc.threeauxiliary;/**
 * @ClassName:CountDownLatchTest
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/3/31 17:12
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 减少计数
 *
 * await() 等待
 *
 * countDown() 减1
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //定义一个循环信息  10个信息
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for(int i = 0; i < 10; i++){
            new Thread(
                    ()->{
                         //如果是 10，最后一个，就等待
                        if ("9".equals(Thread.currentThread().getName())){
                            try {
                                TimeUnit.SECONDS.sleep(4);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // 对信息进行处理
                        log.info(Thread.currentThread().getName()+"离开");
                        //减少1
                        countDownLatch.countDown();
                    }
            ,i+"").start();

        }

        //主线程
        log.info(">>>主线程开始睡觉中");

        countDownLatch.await();

        //全部离开之后，会进行唤醒
        log.info(">>>>全部离开了,"+countDownLatch.getCount());






    }
}
