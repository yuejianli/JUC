package top.yueshushu.juc.callable;/**
 * @ClassName:MyCallableTest
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/3/31 16:54
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;
import sun.awt.windows.ThemeReader;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 用途描述
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 *
 * FutureTask 未来任务
 */
@Log4j
public class MyCallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       // futureTask1();
        futureTask2();

    }

    private static void futureTask2() throws InterruptedException, ExecutionException {
        // 定义 未来任务 FutureTask
        FutureTask futureTask = new FutureTask(
                ()->{
                    TimeUnit.SECONDS.sleep(4);
                    log.info(">>> 进入 callable() 方法");
                    return 200;
                }
        );
        // 启动未来任务
        new Thread( futureTask).start();

        //获取结果
        Object o = futureTask.get();
        // get 时，只获取一次信息
        Object o1 = futureTask.get();

        log.info(">>>输出信息:"+o);
        log.info(">>>输出信息:"+o1);
    }



    private static void futureTask1() throws InterruptedException, ExecutionException {
        // 定义 未来任务 FutureTask
        FutureTask futureTask = new FutureTask(
                new MyCallable()
        );

        // 启动未来任务
        new Thread( futureTask).start();

        //获取结果
        Object o = futureTask.get();

        log.info(">>>输出信息:"+o);
    }
}
