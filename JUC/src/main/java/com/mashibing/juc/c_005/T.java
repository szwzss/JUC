/**
 * 分析一下这个程序的输出
 * @author mashibing
 */

package com.mashibing.juc.c_005;

public class T implements Runnable {

	private   int count = 100;
	
	@Override
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) {
		T t = new T();
		T t1 = new T();
		for(int i=0; i<100; i++) {
			new Thread(t, "THREAD" + i).start();
		}

		for(int i=0; i<100; i++) {
			new Thread(t1, "THREAD-TWO" + i).start();
		}
	}
	
}
