// package demoApp.src.main.java.www.dajiazhongyi.com;

import java.lang.Thread;

/**
 * Hello world!
 *
 */
public class ThreadTest2 {
    public static void main( String[] args ) throws InterruptedException
    {
        System.out.println( "Hello World!" );
        MyThread t1 = new MyThread("张三丰");
        System.out.println(t1.getState());
        System.out.println(t1.getState());
       
        int count = 50;
        t1.start();
        while(count-- >= 0) {
            System.out.println(t1.getState());
            Thread.sleep(200);
        }
        // MyThread t2 = new MyThread("王老吉");
        // MyThread t3 = new MyThread("武则天");
        
        // t2.start();
        // t3.start();
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

        // WAITING
        t.start();
        try {
            t.join(); // Waits for this thread to die. 调用线程等待该线程完成后，才能继续用下运行。
        } catch (InterruptedException e) {
            
        }
        long count = 10000000000L;
        while (count-- >= 0) {

        }
    }
}    


