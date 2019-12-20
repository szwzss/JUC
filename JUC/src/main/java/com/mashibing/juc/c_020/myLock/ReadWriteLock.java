package com.mashibing.juc.c_020.myLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock  {

   int val =0;

     static Lock retlock=new ReentrantLock(); //参数为true表示为公平锁，请对比输出结果

     static java.util.concurrent.locks.ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static   Lock read = readWriteLock.readLock();

    static Lock wirte = readWriteLock.writeLock();



    public static void  read(Lock lock){
        lock.lock();
        try {
            Thread.sleep(1);
            System.out.println("Read");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

   public static void  write(Lock lock,int val){
       lock.lock();
       try {
           Thread.sleep(1);
           val = val;
           System.out.println("write"+val);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }finally {
           lock.unlock();
       }
   }




    public static void main(String[] args) {

        Runnable readR = ()-> read(read);

        Runnable wirteR = ()->write(wirte,new Random().nextInt());


        for(int i=0;i<20;i++) {
            new Thread(readR).start();
        }

        for(int f=0;f<2;f++) {
            new Thread(wirteR).start();
        }
    }
}
