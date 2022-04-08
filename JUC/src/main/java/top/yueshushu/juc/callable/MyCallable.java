package top.yueshushu.juc.callable;/**
 * @ClassName:MyCallable
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/3/31 16:52
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.Callable;

/**
 * 用途描述
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        log.info(">>>进入 call 方法");
        return 100;
    }
}
