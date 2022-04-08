package top.yueshushu.juc.completableFuture.resultmerge;/**
 * @ClassName:NoMergeDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 19:35
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 合并多个任务 anyOf 有一个返回就可以结束了。
 *
 * 执行的顺序是不固定的。 
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 *
 */
@Log4j
public class MergeyAnyNoRelationDemo {
    private static int number = 0;
    public static void main(String[] args) throws Exception {

        CompletableFuture[] resultArray = new CompletableFuture[4];

        //计算两个结果
        CompletableFuture <Integer> future1 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>执行加10的任务 ");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    number += 10;
                    return number;
                }
        );
        resultArray[0]=future1;

        CompletableFuture <Integer> future2 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>>>执行 * 10的任务");
                    number *= 10;
                    return number;
                }
        );
        resultArray[1]=future2;

        CompletableFuture <Integer> future3 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>>>执行  -10 的任务");
                    number -= 10;
                    return number;
                }
        );
        resultArray[2]=future3;

        CompletableFuture <Integer> future4 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>>>执行  /10 的任务");
                    number /= 10;
                    return number;
                }
        );
        resultArray[3]=future4;

        // 多任务合并
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(resultArray);
        log.info(">>> 输出结果:"+objectCompletableFuture.get());
    }
}
