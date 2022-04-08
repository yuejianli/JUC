package top.yueshushu.juc.completableFuture;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 有返回值的  CompletableFuture
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class CompletableFutureHaveDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture future = new CompletableFuture();
        new Thread(()->{
            log.info(Thread.currentThread().getName()+"子线程开始运行");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //执行操作
            future.complete("Success");
        },"线程A").start();
        // get 方法是 阻塞的
        log.info(Thread.currentThread().getName()+"获取结果为:"+future.get());
        log.info("主线程完成，阻塞结束");
    }
}
