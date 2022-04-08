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
[2022-03-31 16:42:21:917 下午]:INFO top.yueshushu.juc.sync8.Sync8.sendEmail(Sync8.java:37)>>>发送 Email
[2022-03-31 16:42:21:917 下午]:INFO top.yueshushu.juc.sync8.Sync8.hello(Sync8.java:41)>>>> Hello,欢迎你
[2022-03-31 16:42:25:924 下午]:INFO top.yueshushu.juc.sync8.Sync8.sendSMS(Sync8.java:34)>>>发送 SMS

 先 email, 后 sms 
 
 */
@Log4j
public class Sync8 {
    // 两个静态方法，锁 类

    public static synchronized void sendSMS() throws Exception{
        // 停顿 4秒 
        TimeUnit.SECONDS.sleep(4);
        log.info(">>>发送 SMS");
    }
    public  synchronized void sendEmail() throws Exception{
        log.info(">>>发送 Email");
    }
    // 增加一个 普通的 hello 方法 
    public void hello() throws Exception{
        log.info(">>>> Hello,欢迎你");
    }

    public static void main(String[] args) {
        // 两个手机， 一个静态，一个普通
        Sync8 sync1 = new Sync8();
        Sync8 sync2 = new Sync8();
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
                sync1.hello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"发送hello").start();
    }

}
