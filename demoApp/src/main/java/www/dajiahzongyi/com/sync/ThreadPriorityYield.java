// package demoApp.src.main.java.www.dajiazhongyi.com;

import java.lang.Thread;

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
    private static Object lock = new Object();
    DistrubuteThread (String name) {
        super(name);
    }
    public void run() {
        synchronized (DistrubuteThread.lock) {
            while (formCount >= 0) {
                System.out.println(Thread.currentThread().getName() + "正在分发：" + formCount--); // 线程不安全
            }
        }
        
    }
}


