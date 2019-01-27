// package demoApp.src.main.java.www.dajiazhongyi.com;

import java.lang.Thread;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        MyThread t1 = new MyThread("张三丰");
        MyThread t2 = new MyThread("王老吉");
        MyThread t3 = new MyThread("武则天");
        t1.start();
        t2.start();
        t3.start();
    }
}


class MyThread extends Thread {
    static int formCount = 50;
    MyThread (String name) {
        super(name);
    }
    public void run() {
        while (formCount >= 0) {
            System.out.println(Thread.currentThread().getName() + "正在分发：" + formCount--); // 线程不安全
        }
    }
}    


