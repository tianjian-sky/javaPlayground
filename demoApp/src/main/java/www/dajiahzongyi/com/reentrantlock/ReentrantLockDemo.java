import java.lang.Thread;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class ReentrantLockDemo {
    public static DistrubuteThread t1 = new DistrubuteThread("张三丰");
    public static DistrubuteThread t2 = new DistrubuteThread("王老吉");
    public static DistrubuteThread t3 = new DistrubuteThread("曾海勇");
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ReentrantLockDemo.t1.start();
        ReentrantLockDemo.t2.start();
        ReentrantLockDemo.t3.start();
    }
}


class DistrubuteThread extends Thread {
    private static int formCount = 50;
    // private static Object lock = new Object();
    private static ReentrantLock lock = new ReentrantLock(true);
    DistrubuteThread (String name) {
        super(name);
    }
    public void run() {
        // synchronized (DistrubuteThread.lock) {
            while (formCount >= 0) {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "正在分发：" + formCount--); // 线程不安全
                System.out.println("1的状态:" + ReentrantLockDemo.t1.getState() + "--2的状态:" + ReentrantLockDemo.t2.getState() + "--3的状态:" + ReentrantLockDemo.t3.getState());
                lock.unlock();
            }
        // }
    }
}
 

