package top.yueshushu.juc.nosafe;/**
 * @ClassName:ArrayListNoSafe
 * @Description TODO
 * @Author zk_yjl
 * @Date 2022/3/31 16:00
 * @Version 1.0
 * @Since 1.0
 **/

import lombok.extern.log4j.Log4j;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 用途描述
 *
 * @author 姓名  YuejianLi
 * @date yyyy-mm-dd
 * ArrayList 并发添加时，出现异常的情况
 */
@Log4j
public class ArrayListNoSafe {
    public static void main(String[] args) {

       // noSafeList();
       // vectorSafe();
      //  collectionSafe();

        copyOnWriteArraySafe();
    }

    private static void copyOnWriteArraySafe() {
        // 使用 Vector 安全的队列
        List<String> arrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            try{
                new Thread(()->{
                    arrayList.add(
                            UUID.randomUUID().toString()
                    );
                    //必须要打印
                    // log.info(arrayList);

                    // sout 会显式打印异常信息
                    System.out.println(arrayList);
                },i+"").start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private static void collectionSafe() {
        // 使用 Vector 安全的队列
        List<String> arrayList = Collections.synchronizedList(
                new ArrayList<>()
        );
        for (int i = 0; i < 100; i++) {
            try{
                new Thread(()->{
                    arrayList.add(
                            UUID.randomUUID().toString()
                    );
                    //必须要打印
                    // log.info(arrayList);

                    // sout 会显式打印异常信息
                    System.out.println(arrayList);
                },i+"").start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    private static void vectorSafe() {
        // 使用 Vector 安全的队列
        List<String> arrayList = new Vector<>();

        for (int i = 0; i < 100; i++) {
            try{
                new Thread(()->{
                    arrayList.add(
                            UUID.randomUUID().toString()
                    );
                    //必须要打印
                    // log.info(arrayList);

                    // sout 会显式打印异常信息
                    System.out.println(arrayList);
                },i+"").start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void noSafeList() {
        List<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
           try{
               new Thread(()->{
                   arrayList.add(
                           UUID.randomUUID().toString()
                   );
                   //必须要打印
                  // log.info(arrayList);

                   // sout 会显式打印异常信息
                   System.out.println(arrayList);
               },i+"").start();
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }
}
