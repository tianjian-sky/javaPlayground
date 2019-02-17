import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


public class BlockingQueueDemo {
    public static void main (String args[]) {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        DistrubuteThread t1 = new DistrubuteThread("张三丰");
        DistrubuteThread t2 = new DistrubuteThread("王老吉");
        DistrubuteThread t3 = new DistrubuteThread("曾海勇");
        TakeThread t4 = new TakeThread("mm1");
        TakeThread t5 = new TakeThread("mm2");
        executor.execute(t1);
        executor.execute(t2);
        executor.execute(t3);
        executor.execute(t4);
        executor.execute(t5);
        executor.shutdown(); // 不再接受新任务
    }
    
}


class DistrubuteThread extends Thread {
    public static ArrayBlockingQueue <Integer> repo = new ArrayBlockingQueue <Integer> (10);
    public static Semaphore sem = new Semaphore(1); // 2. semaphore
    public static int data = 0;
    DistrubuteThread (String name) {
        super(name);
    }
    public void run() {
        for (; DistrubuteThread.data < 50;) {
            try {
                sem.acquire();
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                }
                DistrubuteThread.repo.put(DistrubuteThread.data++);
                System.out.println(Thread.currentThread().getName() + "正在加入：" + DistrubuteThread.data); // 线程不安全
                sem.release();
            } catch (Exception e) {
            }
        }
    }
}

class TakeThread extends Thread {
    private static Semaphore sem = new Semaphore(1); // 2. semaphore
    private int data = 0;
    TakeThread (String name) {
        super(name);
    }
    public void run() {
        while (true) {
            try {
                sem.acquire();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
                
                System.out.println("\t\t\t\t" + Thread.currentThread().getName() + "正在拿：" + DistrubuteThread.repo.take()); // 线程不安全
                sem.release();
            } catch (Exception e) {
            }
        }
    }
}