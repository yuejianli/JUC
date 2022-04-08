package top.yueshushu.juc.sync8;/**
 * @ClassName:Sync1
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/3/31 16:21
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.concurrent.TimeUnit;

/**
 * 用途描述
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 *
[2022-03-31 16:36:54:395 下午]:INFO top.yueshushu.juc.sync8.Sync4.sendEmail(Sync4.java:35)>>>发送 Email
[2022-03-31 16:36:54:395 下午]:INFO top.yueshushu.juc.sync8.Sync4.hello(Sync4.java:39)>>>> Hello,欢迎你
[2022-03-31 16:36:58:404 下午]:INFO top.yueshushu.juc.sync8.Sync4.sendSMS(Sync4.java:32)>>>发送 SMS

 先 Email, 再 SMS
 
 */
@Log4j
public class Sync4 {
    public synchronized void sendSMS() throws Exception{
        // 停顿 4秒 
        TimeUnit.SECONDS.sleep(4);
        log.info(">>>发送 SMS");
    }
    public synchronized void sendEmail() throws Exception{
        log.info(">>>发送 Email");
    }
    // 增加一个 普通的 hello 方法 
    public void hello() throws Exception{
        log.info(">>>> Hello,欢迎你");
    }

    public static void main(String[] args) {
        // 两个实例对象
        Sync4 sync1 = new Sync4();
        Sync4 sync2 = new Sync4();
        new Thread(()->{
            try {
                sync1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"发送SMS").start();

        new Thread(()->{
            try {
                sync2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"发送Email").start();

        new Thread(()->{
            try {
                sync2.hello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"发送hello").start();
    }

}
