package top.yueshushu.juc.completableFuture;/**
 * @ClassName:ThreadApplyDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 15:40
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 *  消费最后处理结果
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class ThreadApplyDemo {
    private static int number = 10 ;

    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(
                ()->{
                    number += 10;
                    return number;
                }
        ).thenApply(
               num ->{
                   return num * num;
               }
        ).thenAccept(
                new Consumer<Integer>() {
                    @Override
                    public void accept(Integer value) {
                        log.info(">>> 所有的子程序处理完成，最后的结果是:"+value);
                    }
                }
        );
        future.get();
        log.info("线程结束");
    }
}
