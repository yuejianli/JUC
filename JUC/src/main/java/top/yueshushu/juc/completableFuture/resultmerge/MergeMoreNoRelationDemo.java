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
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * 合并多个任务 allOf 所有的任务执行完成之后
 *
 * 执行的顺序是不固定的。
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 *
 */
@Log4j
public class MergeMoreNoRelationDemo {
    private static int number = 0;
    public static void main(String[] args) throws Exception {

        List<CompletableFuture> resultList = new ArrayList<>();

        //计算两个结果
        CompletableFuture <Integer> future1 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>执行加10的任务 ");
                    number += 10;
                    return number;
                }
        );
        resultList.add(future1);

        CompletableFuture <Integer> future2 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>>>执行 * 10的任务");
                    number *= 10;
                    return number;
                }
        );
        resultList.add(future2);

        CompletableFuture <Integer> future3 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>>>执行  -10 的任务");
                    number -= 10;
                    return number;
                }
        );
        resultList.add(future3);

        CompletableFuture <Integer> future4 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>>>执行  /10 的任务");
                    number /= 10;
                    return number;
                }
        );
        resultList.add(future4);
        // 多任务合并
        List<Integer> result = resultList.stream().map(
                CompletableFuture<Integer>::join
        ).collect(Collectors.toList());
        log.info(">>> 输出结果:"+result);
    }
}
