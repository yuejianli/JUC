package top.yueshushu.juc.completableFuture;/**
 * @ClassName:NoReturnAsyncDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 10:33
 * @Version 1.0
 * @Since 1.0
 **/

import com.sun.xml.internal.ws.util.CompletedFuture;
import lombok.extern.log4j.Log4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 没有 返回值的信息   CompletableFuture
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class NoReturnAsyncDemo  {
    public static void main(String[] args)  throws Exception {
        CompletableFuture <Void> future = CompletableFuture.runAsync(
                ()->{
                        try{
                            log.info(Thread.currentThread().getName()+"开始执行");
                            TimeUnit.SECONDS.sleep(3);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                }
        );

        log.info(">>>>执行完成");
        // 会同步阻塞
        future.get();
        log.info(">>>>执行结束");

    }
}
