package top.yueshushu.juc.completableFuture.resultmerge;/**
 * @ClassName:NoMergeDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 19:35
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.CompletableFuture;

/**
 * 合并两个任务， 这两个任务之间，有依赖关系。
 * 先执行 future1, 再执行 future2
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class NoMergeDemo {
    private static int number = 0;
    public static void main(String[] args) throws Exception {
        //计算两个结果
        CompletableFuture <Integer> future1 = CompletableFuture.supplyAsync(
                ()->{
                    return number+10;
                }
        );
        CompletableFuture <Integer> future2 = future1.thenCompose(
                i->{
                    return CompletableFuture.supplyAsync(
                            ()->{
                                return i+50;
                            }
                    );
                }
        );

        // 输出信息
        log.info(">>> 输出内容:"+future1.get());
        log.info(">>> 输出内容:"+future2.get());

    }
}
