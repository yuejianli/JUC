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
 * 先停 4秒之后
[2022-03-31 16:34:05:019 下午]:INFO top.yueshushu.juc.sync8.Sync2.sendSMS(Sync2.java:30)>>>发送 SMS
[2022-03-31 16:34:05:022 下午]:INFO top.yueshushu.juc.sync8.Sync2.sendEmail(Sync2.java:33)>>>发送 Email
 */
@Log4j
public class Sync2 {
    public synchronized void sendSMS() throws Exception{
        // 停顿 4秒
        TimeUnit.SECONDS.sleep(4);
        log.info(">>>发送 SMS");
    }
    public synchronized void sendEmail() throws Exception{
        log.info(">>>发送 Email");
    }
    public static void main(String[] args) {
        Sync2 sync1 = new Sync2();
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
