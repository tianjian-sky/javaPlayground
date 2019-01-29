// package demoApp.src.main.java.www.dajiazhongyi.com;

import java.lang.Thread;

/**
 * Hello world!
 *
 */
public class DeamonThreadUserThread {
    public static Object lock = new Object();
    public static void main( String[] args ) throws InterruptedException
    {
        UserThread t1 = new UserThread();
        DeamonThread t2 = new DeamonThread();
        t2.setDaemon(true);
        t1.start();
        t2.start();
    }
}

class UserThread extends Thread {
    public void run () {
        long count = 1000L;
        while(count-- >= 0) {
            System.out.println(">>>用户线程默默工作～:" + count);
        }
    }
}

class DeamonThread extends Thread {
    public void run () {
        while(true) {
            System.out.println("守护线程默默工作～");
        }
    }
}    


