package top.yueshushu.juc.forkjoin;/**
 * @ClassName:NoForkJoinDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/7 10:09
 * @Version 1.0
 * @Since 1.0
 **/

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.extern.log4j.Log4j;

/**
 * 用途描述
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class NoForkJoinDemo {
    public static void main(String[] args) {
        TimeInterval timer = DateUtil.timer();
        timer.start();
        int sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += i;
        }
        log.info(">>>>输出最后结果:" + sum);
        // 4
        log.info(">>>最后执行时间:" + timer.intervalMs());
    }
}
