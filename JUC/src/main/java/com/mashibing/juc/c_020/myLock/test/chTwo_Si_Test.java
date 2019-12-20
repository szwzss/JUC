package com.mashibing.juc.c_020.myLock.test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:         songyafei
 * @Package:        com.mashibing.juc.c_020.myLock.test
 * @ClassName:      chTwoTest
 * @Description:
 *
 * @Param
 * @Exception
 * @CreateDate:     2019/12/19 13:45
 * @UpdateRemark:   The modified content
 * @Version:        1.0
 * @Return :
 **/

public class chTwo_Si_Test<T> {

    /**
     * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
     * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
     *
     * 使用wait和notify/notifyAll来实现
     *
     * 使用Lock和Condition来实现
     * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
     *
     * @author mashibing
     */
     private Lock lock = new ReentrantLock();
     private final static  Integer MAX = 10;
     private  Integer count =0;
     private Condition producer  =lock.newCondition(); //生产者
     private Condition consumer  = lock.newCondition(); //消费者
     final private LinkedList<T> linkList = new LinkedList<>();

     /**
      * @Author:         songyafei
      * @Package:        com.mashibing.juc.c_020.myLock.test
      * @ClassName:      chTwo_Si_Test
      * @Description:     获取集合数量
      * @Param           []
      * @Exception
      * @CreateDate:     2019/12/19 16:20
      * @UpdateRemark:   The modified content
      * @Version:        1.0
      * @Return :        java.lang.Integer
      **/

     public  Integer getCount(){
         if(linkList.size()>0){
             return linkList.size();

         }else{
             return  0;
         }

     }

     /**
      * @Author:         songyafei
      * @Package:        com.mashibing.juc.c_020.myLock.test
      * @ClassName:      chTwo_Si_Test
      * @Description:     获取数据  消费者
      *                  1.先判断容器是否为空   空 = 消费者等待   ！空 = 容器清空首元素
      *                  2.记录数减一   通知生产者生产
      *                  3.释放锁  返回对象
      * @Param           []
      * @Exception
      * @CreateDate:     2019/12/19 16:20
      * @UpdateRemark:   The modified content
      * @Version:        1.0
      * @Return :        T
      **/

     public  T  get() throws InterruptedException {
         T t =null;
         lock.lock();
         while (linkList.size() == 0){
             consumer.await();
         }
         t = linkList.removeFirst();
         count--;
         producer.signalAll();
         lock.unlock();
         return t;
     }

     /**
      * @Author:         songyafei
      * @Package:        com.mashibing.juc.c_020.myLock.test
      * @ClassName:      chTwo_Si_Test
      * @Description:     生产者
  *                      1.先判断容器空间是否已经满了   满=生产者等一等   不满 = 添加 并且数量记录+1
      *                  2.通知消费者进行消费
      *                  3.释放锁 返回数据
      * @Param           [t]
      * @Exception
      * @CreateDate:     2019/12/19 16:24
      * @UpdateRemark:   The modified content
      * @Version:        1.0
      * @Return :        boolean
      **/

     public  boolean  set(T t) throws InterruptedException {
         boolean result =false;
         lock.lock();
         while (linkList.size() == MAX ){
             producer.await();
         }
         result = linkList.add(t);
         ++count;
         consumer.signalAll();
         lock.unlock();
         return  result;
     }




    public  static  void main(String[] args) {
        chTwo_Si_Test<String> data = new chTwo_Si_Test<String>();

        //消费者
            for (int j = 0; j <10 ; j++) {
                new Thread(()->{
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println(data.get()+"count"+data.getCount());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },"c"+j).start();
            }



        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //生产者c
        for (int q = 0; q < 2; q++) {
            new Thread(()->{
                for (int l = 0; l < 25; l++) {
                    try {
                        data.set(Thread.currentThread().getName() + ","+l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"p"+q).start();
        }







    }

}
