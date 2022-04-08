package top.yueshushu.juc.completableFuture;/**
 * @ClassName:ThreadExceptionDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 19:25
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.CompletableFuture;

/**
 * 线程处理 handle
 * i 表示返回值，  ex 表示异常信息。
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class ThreadExceptionDemo2 {
    private static int number = 10;

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    // 有异常信息
                   // int i = 10 / 0;
                    log.info(">>>+10任务处理");
                    number += 10;
                    return number;
                }
        ).handle((i,ex)->{
           log.info(">>>进入 handler 方法");
           if (ex != null) {
               log.info(">>>有异常:"+ex);
               return -1;
           }else{
               log.info(">>>正常输出内容:"+i);
               return i;
           }
        });
        log.info(">>>程序运行结束:" + completableFuture.get());
    }
}
