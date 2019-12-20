package com.mashibing.juc.c_020.myLock;

import java.security.PublicKey;
import java.util.concurrent.Semaphore;

/**
 * @Author:         songyafei
 * @Package:        com.mashibing.juc.c_020.myLock
 * @ClassName:      MySemaphore
 * @Description:     你Semaphore的运用
 * @Param
 * @Exception
 * @CreateDate:     2019/12/17 15:37
 * @UpdateRemark:   The modified content
 * @Version:        1.0
 * @Return :
 **/

public class MySemaphore {



    public  static  void main(String[] args){
        //Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(2);
        //允许一个线程同时执行
        //Semaphore s = new Semaphore(1);

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");

                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    
    }
}
