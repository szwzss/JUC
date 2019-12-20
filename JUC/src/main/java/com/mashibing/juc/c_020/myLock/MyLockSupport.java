package com.mashibing.juc.c_020.myLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author:         songyafei
 * @Package:        com.mashibing.juc.c_020.myLock
 * @ClassName:      LockSupport
 * @Description:     LockSupport 的使用
 * @Param
 * @Exception
 * @CreateDate:     2019/12/17 13:39
 * @UpdateRemark:   The modified content
 * @Version:        1.0
 * @Return :
 **/

public class MyLockSupport {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 10; i++) {

                if (i==5){
                    LockSupport.park();

                }
                System.out.println("start"+i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        });


        thread.start();




        TimeUnit.SECONDS.sleep(8);

        System.out.println(" LockSupport.park");
        LockSupport.unpark(thread);
    }
}
