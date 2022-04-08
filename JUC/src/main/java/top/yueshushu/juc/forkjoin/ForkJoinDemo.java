package top.yueshushu.juc.forkjoin;/**
 * @ClassName:ForkJoinDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 9:38
 * @Version 1.0
 * @Since 1.0
 **/

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.unit.DataUnit;
import lombok.extern.log4j.Log4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 用途描述
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class ForkJoinDemo {
    public static void main(String[] args) {
        TimeInterval timer = DateUtil.timer();
        timer.start();
        //定义执行任务
        CalcTask calcTask = new CalcTask(1,1000);

        //定义执行对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //将任务加入到执行对象里面, 返回对应的结果对象。
        ForkJoinTask<Long> result = forkJoinPool.submit(calcTask);

        try{
            //获取对应的结果信息
            log.info("获取结果:"+result.get());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            forkJoinPool.shutdown();
        }
        // 25
        log.info(">>>最后执行时间:"+timer.intervalMs());
    }
}
