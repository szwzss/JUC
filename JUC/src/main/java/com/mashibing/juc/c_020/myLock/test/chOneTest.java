package com.mashibing.juc.c_020.myLock.test;

import com.mashibing.juc.c_020_01_Interview.T02_WithVolatile;
import com.mashibing.juc.c_020_01_Interview.T08_Semaphore;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @Author:         songyafei
 * @Package:        com.mashibing.juc.c_020.myLock.test
 * @ClassName:      chOneTest
 * @Description:
 * @Param
 * @Exception
 * @CreateDate:     2019/12/17 19:53
 * @UpdateRemark:   The modified content
 * @Version:        1.0
 * @Return :
 **/


public class chOneTest {



    /**
     * 曾经的面试题：（淘宝？）
     * 实现一个容器，提供两个方法，add，size
     * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
     *
     * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，
     * 而且，如果在if 和 break之间被别的线程打断，得到的结果也不精确，
     * 该怎么做呢？
     * @author mashibing
     */



    //添加volatile，使t2能够得到通知
    //volatile List lists = new LinkedList();
    volatile List lists = Collections.synchronizedList(new LinkedList<>());

    static Thread t1 = null, t2 = null;
    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        T08_Semaphore c = new T08_Semaphore();
        Semaphore s = new Semaphore(1);

        t1 = new Thread(() -> {
            try {


                s.acquire();

                for (int i = 0; i < 5; i++) {
                    c.add(i);
                    System.out.println("t1 add" + i);
                }

                s.release();

                t2.start();
                t2.join();

                s.acquire();

                for (int j = 5; j < 10; j++) {
                    System.out.println("t1 add" + j);
                }

                s.release();

            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");

        t2 = new Thread(() -> {

            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 start");
            s.release();
        }, "t2");

//        t2.start();
        t1.start();

}
}
