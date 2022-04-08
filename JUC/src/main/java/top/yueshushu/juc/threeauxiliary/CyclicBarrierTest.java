package top.yueshushu.juc.threeauxiliary;/**
 * @ClassName:CyclicBarrierTest
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/3/31 17:22
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 循环栅栏
 * <p>
 * 会进行加1
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class CyclicBarrierTest {
    private final static int NUMBER = 7;

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,
                () -> {
                    log.info("满足条件，开始执行程序>>>>");

                    log.info(">>>集齐了" + NUMBER + "龙珠，召唤");
                });

        for (int i = 0; i < NUMBER; i++) {
            new Thread(
                    () -> {
                        if("3号".equals(Thread.currentThread().getName())){
                            //
                            log.info(">>>>这是第3号龙珠，开始争斗");
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            log.info(">>>> 拿到了第3号龙珠");
                        }else{
                            log.info( Thread.currentThread().getName()+"收集到了");
                        }
                        try {
                            //每一次 await() 都会 加  1操作
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                    , i + "号").start();

        }
    }
}
