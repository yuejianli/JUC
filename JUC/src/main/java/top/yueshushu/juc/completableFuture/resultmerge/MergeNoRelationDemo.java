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

/**
 * 合并两个，没有依赖关系的。
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class MergeNoRelationDemo {
    private static int number = 0;
    public static void main(String[] args) throws Exception {
        //计算两个结果
        CompletableFuture <Integer> future1 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>执行加10的任务 ");
                    number += 10;
                    return number;
                }
        );
        CompletableFuture <Integer> future2 = CompletableFuture.supplyAsync(
                ()->{
                    log.info(">>>>>执行 * 10的任务");
                    number *= 10;
                    return number;
                }
        );
        // 将两个结果，进行合并
        CompletableFuture<List<Integer>> resultFuture = future1.thenCombine(
                future2, new BiFunction<Integer, Integer, List<Integer>>() {
                    @Override
                    public List<Integer> apply(Integer integer, Integer integer2) {
                        List<Integer> list = new ArrayList<>();
                        list.add(integer);
                        list.add(integer2);
                        return list;
                    }
                }
        );
        log.info("合并的结果:"+resultFuture.get());
    }
}
