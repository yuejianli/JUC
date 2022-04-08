package top.yueshushu.juc.sync;

/**
 * @ClassName:IncrDecrSyncDemo
 * @Description 两个线程处理， 一个添加1，一个减少1.
 * @Author zk_yjl
 * @Date 2022/3/25 14:13
 * @Version 1.0
 * @Since 1.0
 **/
public class IncrDecrSyncDemo {
    public static void main(String[] args) {
       // IncrDecrDto incrDecrDto = new IncrDecrDto();
        IncrDecrLockDto incrDecrDto = new IncrDecrLockDto();
        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        incrDecrDto.incr();
                    }
                },
                "线程A").start();

        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        incrDecrDto.decr();
                    }
                },
                "线程B").start();
        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        incrDecrDto.incr();
                    }
                },
                "线程C").start();

        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        incrDecrDto.decr();
                    }
                },
                "线程D").start();
    }
}
