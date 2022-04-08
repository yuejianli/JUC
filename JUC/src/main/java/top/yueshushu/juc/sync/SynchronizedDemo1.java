package top.yueshushu.juc.sync;

import lombok.extern.log4j.Log4j;
/**
 * @ClassName:SynchronizedDemo1
 * @Description synchronized 使用  一个普通的志票的程序
 * @Author zk_yjl
 * @Date 2022/3/24 11:50
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j
public class SynchronizedDemo1 {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(
                () ->{
                    for(int i =0;i<40;i++){
                        ticket.sale();
                    }
                }
                ,"线程A").start();

        new Thread(
                () ->{
                    for(int i =0;i<40;i++){
                        ticket.sale();
                    }
                }
                ,"线程B").start();

        new Thread(
                () ->{
                    for(int i =0;i<40;i++){
                        ticket.sale();
                    }
                }
        ,"线程C").start();

    }
}
/**
  不加同步时，  public void sale()  会卖出30张票， 但是卖出票的顺序 并不是从  30~1
  加同步时，  public synchronized void sale()   会卖出30张票，卖出票的顺序是从 30~1 依次递减的
 */
@Log4j
class Ticket {
    private int socketNum = 30;

    public void sale(){
        if(socketNum > 0){
            socketNum --;
            log.info(Thread.currentThread().getName()+">>>>卖出第"+socketNum+"张票");
        }
    }
}
