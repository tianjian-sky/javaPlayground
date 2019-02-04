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
        cat.setPriority(Thread.MAX_PRIORITY);
        cat.start();
        dog.start();
        for (long i = 0L; i < 300L; i++) {
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                
            }
            if(i == 10L) {
                dog.interrupt(); // 处于等待锁的状态调用interrupt，则抛出异常，接下来就不等了，爱干嘛干嘛去
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
        try {
            TimeLock.TIME_LOCK.lockInterruptibly();
            try {
                System.out.println("cat sleep start");
                Thread.sleep(5000);
                System.out.println("cat sleep end");
            } catch (Exception e) {
            }
           
            while(count-- > 0) {
                // try {
                //     Thread.sleep(100);
                // } catch (Exception e) {
                //     //TODO: handle exception
                // }
                count--;
                System.out.println("cat mowing...");
            }
        } catch (Exception e) {
            System.out.println("cat被中断");
        } finally {
            TimeLock.TIME_LOCK.unlock();
        }
        
    }
}

class Dog extends Thread {
    int count = 100;
    public void run () {
        try {
            TimeLock.TIME_LOCK.lockInterruptibly();
            try {
                while(count-- > 0) {
                    count--;
                    System.out.println("Dog mowing...");
                }
            } catch (Exception e) {
            } finally {
                TimeLock.TIME_LOCK.unlock();
            }
        } catch (Exception e) {
            System.out.println("dog异常,不等待锁了，主动退出，爱干嘛干嘛去❤️");
        }
    }
}

