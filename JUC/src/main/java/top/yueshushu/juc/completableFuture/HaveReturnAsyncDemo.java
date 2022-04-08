package top.yueshushu.juc.completableFuture;/**
 * @ClassName:HaveReturnAsyncDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 15:19
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 有返回值的信息
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class HaveReturnAsyncDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture <String> future = CompletableFuture.supplyAsync(
                ()->{
                    try{
                         log.info(Thread.currentThread().getName()+"开始执行任务");
                         TimeUnit.SECONDS.sleep(3);
                         return "Hello, Success";
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return "Fail";
                }
        );
        log.info(">>>>开始执行程序");
        log.info(">>>获取子程序的结果信息:" + future.get());
        log.info(">>>>>线程运行结束");
    }
}
