package top.yueshushu.juc.callable;/**
 * @ClassName:FutureTaskTest
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/3/31 17:05
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 用途描述
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info(">>>>> 开始处理信息");
        //定义10个线程信息集合
        List<FutureTask> futureTaskList = new ArrayList<>(10);
        for(int i=0 ; i<10; i++){
            FutureTask futureTask = new FutureTask(
                    () ->{
                        TimeUnit.SECONDS.sleep(2);
                        return new Random().nextInt(100);
                    }
            );
            futureTaskList.add(futureTask);
        }

        //同时执行信息
        for(FutureTask futureTask : futureTaskList ){
            new Thread(futureTask).start();
        }

        //获取信息
        int result = 0;
        // 最后汇总信息

        for(FutureTask futureTask:futureTaskList ){
            result += Integer.parseInt( futureTask.get().toString());
        }
        log.info(">>>>>获取最后的结果信息:" + result);
    }
}
