package com.mashibing.juc.c_020.myLock.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:         songyafei
 * @Package:        com.mashibing.juc.c_020.myLock.test
 * @ClassName:      chTwoTest
 * @Description:
 *                 通过两个线程分别完成输出，一个线程输出大写A-Z，另一个线程输出小写a-z，并且交互输出 (2012-09-07 16:12:19)
 * @Param
 * @Exception
 * @CreateDate:     2019/12/19 13:45
 * @UpdateRemark:   The modified content
 * @Version:        1.0
 * @Return :
 **/

public class chTwo_San_Test {


    public  static  void main(String[] args) {

        Lock lock = new ReentrantLock();


         Condition one = lock.newCondition();
         Condition two = lock.newCondition();



        Thread  t1= new Thread(()->{

                try {
                    lock.lock();
                    TimeUnit.SECONDS.sleep(1);
                    for (char i = 'A'; i < 'Z'; i++) {

                        System.out.println("one"+i);

                        one.await(1,TimeUnit.SECONDS);
                        two.signalAll();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }




        },"T1");


        Thread t2 =  new Thread(()->{
            try {
                lock.lock();


                for (char i = 'a'; i < 'z'; i++) {

                        System.out.println("two"+i);

                        two.await(2,TimeUnit.SECONDS);
                        one.signalAll();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        },"T2");
        t1.start();
        t2.start();

    }

}
