package top.yueshushu.juc.forkjoin;/**
 * @ClassName:CalcTask
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 9:39
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.io.Serializable;
import java.util.concurrent.RecursiveTask;

/**
 * 计算任务
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class CalcTask extends RecursiveTask<Long> implements Serializable {
    private int start;
    private int end;
    private Long sum =0L;

    public CalcTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        log.info(">>>开始计算" + start + ",到" + end + "的计算结果");
        if (end - start <= 100) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else{
            //进行切分
            int middle = start + 100;
            CalcTask calcTask1 = new CalcTask(start,middle);
            CalcTask calcTask2 = new CalcTask(middle+1,end);
            // 会异步执行
            calcTask1.fork();
            calcTask2.fork();

            //同步阻塞获取执行的结果
            sum = calcTask1.join() + calcTask2.join();
        }
        return sum;
    }


}
