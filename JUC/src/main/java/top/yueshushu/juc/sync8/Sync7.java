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
[2022-03-31 16:40:59:097 下午]:INFO top.yueshushu.juc.sync8.Sync7.hello(Sync7.java:40)>>>> Hello,欢迎你
[2022-03-31 16:40:59:097 下午]:INFO top.yueshushu.juc.sync8.Sync7.sendEmail(Sync7.java:36)>>>发送 Email
[2022-03-31 16:41:03:102 下午]:INFO top.yueshushu.juc.sync8.Sync7.sendSMS(Sync7.java:33)>>>发送 SMS

 先 email, 后 sms
 
 */
@Log4j
public class Sync7 {
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
        // 一个手机， 一个静态，一个普通
        Sync7 sync1 = new Sync7();
        new Thread(()->{
            try {
                sync1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"发送SMS").start();

        new Thread(()->{
            try {
                sync1.sendEmail();
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
