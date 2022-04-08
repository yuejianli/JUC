package top.yueshushu.juc.blockqueue;/**
 * @ClassName:BlockQueueDemo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/4/1 14:17
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列设置信息
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
@Log4j
public class BlockQueueDemo {
    public static void main(String[] args) {
        //
        // addRemoveExceptionDemo();

        //offerPollBooleanDemo();
        //putTakeBlockingDemo();


        offerPollTimeoutDemo();

    }
    /**
     当空间不足时，会等待一些时间， 超时时会退出。

     */
    private static void offerPollTimeoutDemo() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        new Thread(
                ()->{
                    try{
                        // 添加元素
                        blockingQueue.offer("A");
                        blockingQueue.offer("B");
                        blockingQueue.offer("C");
                        log.info(">>>添加3个元素成功");
                        //放置元素，超时退出
                        boolean d = blockingQueue.offer("D", 1, TimeUnit.SECONDS);
                        if(d){
                            log.info(">>>添加元素成功");
                        }else{
                            log.info(">>>添加元素失败");
                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
        ,"offer添加元素").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(
                ()->{
                    try{
                        // 移除元素
                        blockingQueue.poll();
                        blockingQueue.poll();
                        blockingQueue.poll();
                        log.info("移除元素成功");
                        //移除元素，超时退出
                        blockingQueue.poll(1,TimeUnit.SECONDS);


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                ,"poll 移除元素").start();


    }

    /**
     当空间不足时， put 和 take 均会阻塞
     */
    private static void putTakeBlockingDemo() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        //放置元素

        new Thread(
                ()->{
                   try{
                       blockingQueue.put("A");
                       log.info(">>>>>添加元素A成功");
                       blockingQueue.put("B");
                       log.info(">>>>>添加元素B成功");
                       blockingQueue.put("C");
                       log.info(">>>>>添加元素C成功");
                       blockingQueue.put("D");
                       log.info(">>>>>添加元素D成功");
                       TimeUnit.SECONDS.sleep(4);

                       blockingQueue.put("E");
                       log.info(">>>>>添加元素E成功");
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                }
        ,"添加元素").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //开始消费

        new Thread(
                ()->{
                    try{
                        Object a =blockingQueue.take();
                        log.info("取出元素:"+a);

                        Object b =blockingQueue.take();
                        log.info("取出元素:"+b);

                        Object c =blockingQueue.take();
                        log.info("取出元素:"+c);

                        Object d =blockingQueue.take();
                        log.info("取出元素:"+d);


                        Object e =blockingQueue.take();
                        log.info("取出元素:"+e);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
        ,"删除元素").start();


    }

    /**
      当空间不足时, 添加返回 false, 取出为 null
     */
    private static void offerPollBooleanDemo() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer("A");
        blockingQueue.offer("B");
        blockingQueue.offer("C");
        boolean offerFlag = blockingQueue.offer("D");
        // 不能添加成功，返回 false
        log.info(">>>是否添加元素 D 成功:"+offerFlag);

        //进行删除

        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        // 取不出元素，返回 null
        Object obj = blockingQueue.poll();
        log.info(">>>>移除掉的元素:"+obj);
    }

    /**
     当空间不足，或者空间塞满之后， 会抛出相应的异常。
     */
    private static void addRemoveExceptionDemo() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        //添加元素
        blockingQueue.add("A");
        blockingQueue.add("B");
        blockingQueue.add("C");
        //添加元素，会报错。 Exception in thread "main" java.lang.IllegalStateException: Queue full
       //  blockingQueue.add("D");

       log.info("获取元素信息:"+blockingQueue.element());

       //删除元素
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();

        // 删除元素会报错。  Exception in thread "main" java.util.NoSuchElementException
        blockingQueue.remove();

    }
}
