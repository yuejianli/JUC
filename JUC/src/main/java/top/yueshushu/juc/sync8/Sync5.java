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
[2022-03-31 16:38:42:981 下午]:INFO top.yueshushu.juc.sync8.Sync5.hello(Sync5.java:41)>>>> Hello,欢迎你
[2022-03-31 16:38:46:980 下午]:INFO top.yueshushu.juc.sync8.Sync5.sendSMS(Sync5.java:34)>>>发送 SMS
[2022-03-31 16:38:46:980 下午]:INFO top.yueshushu.juc.sync8.Sync5.sendEmail(Sync5.java:37)>>>发送 Email

 先 SMS 后 email
 
 */
@Log4j
public class Sync5 {
    // 两个静态方法，锁 类

    public static synchronized void sendSMS() throws Exception{
        // 停顿 4秒 
        TimeUnit.SECONDS.sleep(4);
        log.info(">>>发送 SMS");
    }
    public static synchronized void sendEmail() throws Exception{
        log.info(">>>发送 Email");
    }
    // 增加一个 普通的 hello 方法 
    public void hello() throws Exception{
        log.info(">>>> Hello,欢迎你");
    }

    public static void main(String[] args) {
        // 两个实例对象
        Sync5 sync1 = new Sync5();
        new Thread(()->{
            try {
                Sync5.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"发送SMS").start();

        new Thread(()->{
            try {
                Sync5.sendEmail();
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
