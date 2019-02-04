// package demoApp.src.main.java.www.dajiazhongyi.com;

import java.lang.Thread;
import java.util.concurrent.Semaphore;

/**
 * Hello world!
 *
 */
public class ThreadPriorityYield {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DistrubuteThread t1 = new DistrubuteThread("张三丰");
        DistrubuteThread t2 = new DistrubuteThread("王老吉");
        DistrubuteThread t3 = new DistrubuteThread("曾海勇");
        t1.start();
        t2.start();
        t3.start();
    }
}


class DistrubuteThread extends Thread {
    private static int formCount = 50;
    private static Object lock = new Object(); // 1.synchronized
    private static Semaphore sem = new Semaphore(1); // 2. semaphore
    DistrubuteThread (String name) {
        super(name);
    }
    public void run() {
        while (formCount >= 0) {
            synchronized (DistrubuteThread.lock) {
                try {
                    // sem.acquire();
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    System.out.println(Thread.currentThread().getName() + "正在分发：" + formCount--); // 线程不安全
                    // sem.release();
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        }
    }
}


 