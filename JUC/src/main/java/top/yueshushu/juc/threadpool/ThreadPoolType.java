package top.yueshushu.juc.threadpool;/**
 * @ClassName:ThreadPoolType
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/2 10:29
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.*;

/**
 * 线程池的种类信息
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class ThreadPoolType {
    /*
     * 线程池构建的时候，会有 七 个参数。
     * 依次是:
     *
     * corePoolSize  线程池的核心线程数
     * maximumPoolSize 能容纳的最大线程数
     * keepAliveTime 空闲线存活时间
     * unit   存活的时间单位
     * workQueue  存放提交但未执行任务的队列
     * threadFactory 创建线程的工厂类，
     *  handler: 等待队列满后的拒绝策略
     */

    /*
     *  newCachedThreadPool
     *   可缓存线程池
     *
     *
     * newFixedThreadPool
     *   可重用的，固定线程数的线程池。
     *
     *
     * newSingleThreadExecutor
     *    单个 worker 线程的 Executor ,以无界队列方式运行该线程。
     *
     *
     * newScheduleThreadPool
     *
     *    支持定时以及周期性执行任务。
     *
     * newWorkStealingPool
     *
     *  创建一个拥有多个任务队列的线程池， 可以减少连接数。

     * */
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(2);
        //1. 创建线程池服务
        ExecutorService executorService = new ThreadPoolExecutor(
                3,3,10, TimeUnit.SECONDS,
                blockingQueue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

       try{
           for(int i = 0 ;i < 20 ; i++ ){
               // .execute 执行线程
               executorService.execute(
                       ()->{
                           try{
                               String currentThreadName = Thread.currentThread().getName();
                               log.info(currentThreadName+"开始卖票");
                               TimeUnit.SECONDS.sleep(2);
                               log.info(currentThreadName+"卖票结束");
                           }catch (Exception e){
                               e.printStackTrace();
                           }

                       }
               );



           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           //关闭线程池
           executorService.shutdown();
       }

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
