package top.yueshushu.juc.reenreadAndwriteLock;/**
 * @ClassName:NoSafeMap
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/1 10:08
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用途描述
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class SafeMap {


    public static void main(String[] args) throws Exception {
        SafeMapDto noSafeMap = new SafeMapDto();

        // 当线程数过多时，会出现异常  Exception in thread "13" Exception in thread "34" java.util.ConcurrentModificationException
        //创建线程放数据
        for (int i = 1; i <= 50; i++) {
            final int num = i;
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        noSafeMap.put(num + "", num + "");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, String.valueOf(i)).start();
        }

        TimeUnit.MICROSECONDS.sleep(300);

        //创建线程取数据
        for (int i = 1; i <= 50; i++) {
            final int num = i;
            new Thread(() -> {
                try {
                    noSafeMap.get(num + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}

@Log4j
class SafeMapDto {
    //里面放置一个Map 信息
    private Map<String, String> map = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // 提供两个方法，一个是写，一个是读

    /**
     * 一个写方法
     */
    public void put(String key, String value) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(300);
        lock.writeLock().lock();
        try {
            System.out.println(">>>开始放置信息," + key + ",值是:" + value);
            map.put(key, value);
            System.out.println(">>>>放置信息成功" + map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }

    /**
     * 一个读方法
     */
    public String get(String key) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(300);
        lock.readLock().lock();
        String result = null;
        try {
            result = map.get(key);
            System.out.println(">>>>成功获取到 " + key + "的信息是:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
            return result;
        }
    }
}
