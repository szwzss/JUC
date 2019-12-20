package com.mashibing.juc.c_020.myLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class CountDown extends  Thread {

    public static void main(String[] args) throws InterruptedException {
       getCount();
    }
    public  static void getCount() throws InterruptedException {
        Thread [] thread = new Thread[100];
        CountDownLatch locah = new CountDownLatch(((Thread[]) thread).length);
        for (int i=0;i<thread.length;i++){
            thread[i] = new Thread(()->{
                int result = 0;
                for (int j=0;j<10000;j++){
                    result +=j;
                    System.out.println(locah.getCount());
                    locah.countDown();

                }
            });
        }

        for (int t=0;t<thread.length;t++){
            thread[t].start();
        }


        locah.await();
        System.out.println("end");
    }
}
