package com.mashibing.juc.c_020.myLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author:         songyafei
 * @Package:        com.mashibing.juc.c_020.myLock
 * @ClassName:      MyCyclicBarrier
 * @Description:     栅栏
 * @Param
 * @Exception
 * @CreateDate:     2019/12/17 14:41
 * @UpdateRemark:   The modified content
 * @Version:        1.0
 * @Return :
 **/

public class MyCyclicBarrier {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(90,new Runnable(){
                @Override
                public void run(){
                    System.out.println("go go go!");
                }
        });


        for (int i = 0; i <450; i++) {
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
