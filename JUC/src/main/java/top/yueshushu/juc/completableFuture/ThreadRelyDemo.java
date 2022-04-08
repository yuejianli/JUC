package top.yueshushu.juc.completableFuture;/**
 * @ClassName:ThreadRelyDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 15:24
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 线程之间依赖处理
 * 先执行 +10 的操作，再执行 平方 的操作
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class ThreadRelyDemo {
    private static int number = 10;
    public static void main(String[] args) throws Exception {


        CompletableFuture future = CompletableFuture.supplyAsync(
                () ->{
                   try{
                       log.info(">>> 线程加10操作");
                       number += 10;
                       TimeUnit.SECONDS.sleep(1);
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                    return number;
                }
        )       // 再执行操作
                .thenApply(
                num ->{
                    log.info(">>>>执行程序，获取 number值："+num);
                    return num * num;
                }
        );

        log.info("获取最后的结果信息:"+future.get());
        log.info("线程结束");



    }
}
