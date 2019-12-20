package com.mashibing.juc.c_020.myLock.test;

import java.util.concurrent.Semaphore;

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

public class chTwo_Two_Test {


    public  static  void main(String[] args) {


      final Object ob = new Object();


        Thread  t1= new Thread(()->{

                try {

                    char da = 'A';
                    for (int i = 0; i < 26; i++) {
                        synchronized (ob){
                            System.out.print("one"+da);
                            da++;
                            ob.wait();
                            ob.notify();
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }




        },"T1");


        Thread t2 =  new Thread(()->{
            try {

                char xiao = 'a';
                for (int i = 0; i < 26; i++) {

                    synchronized (ob){
                        System.out.print("two"+xiao);

                        xiao++;
                        ob.notify();
                        ob.wait();
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"T2");
        t1.start();
        t2.start();

    }

}
