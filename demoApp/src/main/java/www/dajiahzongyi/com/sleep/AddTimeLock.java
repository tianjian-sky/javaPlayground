import java.sql.Time;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 用信号量代替synchronized ,notify, wait
 * 实现生产者消费者问题
 * 
 */
public class AddTimeLock {
    public static final Object lock = new Object();
    public static void main(String args[]) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        cat.start();
        dog.start();
        for (long i = 0L; i < 100L; i++) {
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                //TODO: handle exception
            }
            System.out.println("cat status:" + cat.getState() + "<---->" + "dog status:" + dog.getState());
        }
    }
}

class TimeLock {
    public static final ReentrantLock TIME_LOCK = new ReentrantLock();
}

class Cat extends Thread{
    int count = 100;
    public void run () {
        synchronized (AddTimeLock.lock) {
            try {
                System.out.println("cat sleep start");
                Thread.sleep(10000);
                System.out.println("cat sleep end");
            } catch (Exception e) {
                //TODO: handle exception
            }
           
            while(count-- > 0) {
                count--;
                System.out.println("cat mowing...");
            }
        }
    }
}

class Dog extends Thread {
    int count = 100;
    public void run () {
        synchronized (AddTimeLock.lock) {
            while(count-- > 0) {
                count--;
                System.out.println("Dog mowing...");
            }
        }
    }
}

