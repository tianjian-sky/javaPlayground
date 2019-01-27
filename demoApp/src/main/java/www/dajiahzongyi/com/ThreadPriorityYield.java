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
        MyThread1 t1 = new MyThread1("张三丰");
        MyThread2 t2 = new MyThread2("王老吉");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}


class MyThread1 extends Thread {
    int formCount = 50;
    MyThread1 (String name) {
        super(name);
    }
    public void run() {
        while (formCount >= 0) {
            System.out.println(Thread.currentThread().getName() + "正在分发：" + formCount--); // 线程不安全
        }
    }
}
class MyThread2 extends Thread {
    int formCount = 50;
    MyThread2 (String name) {
        super(name);
    }
    /**
     * 让出处理器资源意愿，调度者可忽略
     * yield: A hint to the scheduler that the current thread is willing to yield its current use of a processor.
     * The scheduler is free to ignore this hint.
     */
    public void run() {
        while (formCount >= 0) {
            Thread.yield();
            System.out.println(Thread.currentThread().getName() + "正在分发：" + formCount--); // 线程不安全
        }
    }
}        


