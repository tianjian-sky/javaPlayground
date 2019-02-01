// package demoApp.src.main.java.www.dajiazhongyi.com;

import java.lang.Thread;

/**
 * Hello world!
 *
 */
public class DeadLock {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        MyThread1 t1 = new MyThread1("张三丰");
        MyThread2 t2 = new MyThread2("王老吉");
        t1.start();
        t2.start();
    }
}

class PublickLock {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();
}

class MyThread1 extends Thread {
    MyThread1 (String name) {
        super(name);
    }
    public void run() {
        synchronized (PublickLock.lock1) {
            System.out.println("1-1进来拉");
            synchronized (PublickLock.lock2) {
                System.out.println("1-2进来拉");
            }
        }
        System.out.println("1结束啦");
    }
}

class MyThread2 extends Thread {
    MyThread2 (String name) {
        super(name);
    }
    public void run() {
        synchronized (PublickLock.lock2) {
            System.out.println("2-1进来拉");
            synchronized (PublickLock.lock1) {
                System.out.println("2-2进来拉");
            }
        }
        System.out.println("2结束啦");
    }
}

