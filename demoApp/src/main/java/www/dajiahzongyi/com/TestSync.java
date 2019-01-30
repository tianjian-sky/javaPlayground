// package demoApp.src.main.java.www.dajiazhongyi.com;

import java.lang.Thread;

/**
 * Hello world!
 *
 */
public class TestSync {
    public static Object lock = new Object();
    public static void main( String[] args ) throws InterruptedException
    {
        ThreadsA ta = new ThreadsA("a");
        ThreadsB tb = new ThreadsB("b");
        ta.start();
        tb.start();
    }
}


class ThreadsA extends Thread {
    ThreadsA (String name) {
        super(name);
    }
    public void run() {
        synchronized (TestSync.lock) { // 获取锁
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println("A running!");
            }
            synchronized (TestSync.lock) { // 获取锁 ，可重入+1
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("A inner running!");
                }
            }
        }
    }
}    

class ThreadsB extends Thread {
    ThreadsB (String name) {
        super(name);
    }
    public void run() {
        synchronized (TestSync.lock) {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
    
                }
                System.out.println("B running!");
            }
        }
    }
}   


