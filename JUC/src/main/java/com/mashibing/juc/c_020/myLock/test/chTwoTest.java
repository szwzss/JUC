package com.mashibing.juc.c_020.myLock.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author:         songyafei
 * @Package:        com.mashibing.juc.c_020.myLock.test
 * @ClassName:      chTwoTest
 * @Description:     练习题二
 *                 通过两个线程分别完成输出，一个线程输出大写A-Z，另一个线程输出小写a-z，并且交互输出 (2012-09-07 16:12:19)
 * @Param
 * @Exception
 * @CreateDate:     2019/12/19 13:45
 * @UpdateRemark:   The modified content
 * @Version:        1.0
 * @Return :
 **/

public class chTwoTest {


    public  static  void main(String[] args) {


        Semaphore sm = new Semaphore(2);
        Thread t1 =null;
        Thread t2 =null;

         t1= new Thread(()->{
            try {
                sm.acquire();
                char da = 'A';
                for (int i = 0; i < 26; i++) {
                    System.out.println("one"+da);
                    da++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

                sm.release();
            }

        },"T1");


        t2 =  new Thread(()->{
            try {
                sm.acquire();
                char xiao = 'a';
                for (int i = 0; i < 26; i++) {
                    System.out.println("two"+xiao);
                    xiao++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

                sm.release();
            }

        },"T2");

    }

}
