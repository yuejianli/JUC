package top.yueshushu.juc.sync;

import lombok.extern.log4j.Log4j;

/**
 * @ClassName:IncrDecrDto
 * @Description 加1 和减1的方法
 * @Author zk_yjl
 * @Date 2022/3/25 14:07
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j
public class IncrDecrDto {
    private int num = 0;

    /**
     * 加1的方法
     *
     * @param
     * @return void
     * @date 2022/3/25 14:07
     * @author zk_yjl
     */
    public synchronized void incr() {
       while (num != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        log.info(Thread.currentThread().getName() + "获得值是:" + num);
        this.notifyAll();
    }

    /**
     * 减1的方法
     *
     * @param
     * @return void
     * @date 2022/3/25 14:08
     * @author zk_yjl
     */
    public synchronized void decr() {
        while (num != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        log.info(Thread.currentThread().getName() + "获得值是:" + num);
        this.notifyAll();
    }
}
