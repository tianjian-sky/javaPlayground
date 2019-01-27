// package demoApp.src.main.java.www.dajiazhongyi.com;

import java.lang.Thread;

/**
 * Hello world!
 *
 */
public class BlockThread {
    public static Object lock = new Object();
    public static void main( String[] args ) throws InterruptedException
    {
        System.out.println( "Hello World!" );
        MyThread t1 = new MyThread("张三丰");
        System.out.println(t1.getState());
        System.out.println(t1.getState());
       
        int count = 50;
        t1.start();

        synchronized (lock) {
            while(count-- >= 0) {
                System.out.println(t1.getState());
                Thread.sleep(200);
            }
        }
    }
}


class MyThread extends Thread {
    static int formCount = 50;
    MyThread (String name) {
        super(name);
    }
    public void run() {
        // TIMED_WAITING
        // try {
        //     Thread.sleep(2000);
        // } catch (InterruptedException e) {
            
        // }

        // 匿名内部类
        Thread t = new Thread() {
            public void run () {
                long count = 10000000000L;
                while (count-- >= 0) {

                }
            }
        };

        t.start();
        // BLOCKED 锁不释放之前就一直block
        synchronized (BlockThread.lock) {
            long count = 10000000000L;
            while (count-- >= 0) {
                System.out.println("t2 running");
            }
        }
    }
}    


