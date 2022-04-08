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
[2022-03-31 16:33:02:266 下午]:INFO top.yueshushu.juc.sync8.Sync1.sendSMS(Sync1.java:28)>>>发送 SMS
[2022-03-31 16:33:02:269 下午]:INFO top.yueshushu.juc.sync8.Sync1.sendEmail(Sync1.java:31)>>>发送 Email

 */
@Log4j
public class Sync1 {
    public synchronized void sendSMS() throws Exception{
        log.info(">>>发送 SMS");
    }
    public synchronized void sendEmail() throws Exception{
        log.info(">>>发送 Email");
    }
    public static void main(String[] args) {
        Sync1 sync1 = new Sync1();
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
    }

}
