package top.yueshushu.juc.blockqueue;/**
 * @ClassName:BlockingQueueType
 * @Description 描述信息
 * @Author zk_yjl
 * @Date 2022/4/1 17:43
 * @Version 1.0
 * @Since 1.0
 **/

import java.util.concurrent.*;

/**
 * 用途描述
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 */
public class BlockingQueueType {
    public static void main(String[] args) {
        /*
        * 由数组结构组成的有界阻塞队列
        * */
        BlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        /*
          由链表结构组成的有界 (大小默认是 Integer.MAX_VALUE) 的阻塞队列
         */
        BlockingQueue linkedBlocingQueue = new LinkedBlockingQueue();

        /*
        * 使用优先级队列实现的延迟无界阻塞队列
        *
        * */
        BlockingQueue delayQueue = new DelayQueue();

        /*
        * 支持优先级排序的无界阻塞队列
        * */
        BlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();

        /*
        * 不存储元素的阻塞队列，要单个元素的队列
        * */
        BlockingQueue synchronousQueue = new SynchronousQueue();

        /*
        * 由链表组成的无界阻塞队列
        * */
        BlockingQueue linkedTransferQueue = new LinkedTransferQueue();

        /*
        * 由链表组成的双向阻塞队列
        * */
        BlockingQueue linkedBlockingDeque = new LinkedBlockingDeque();
    }
}
