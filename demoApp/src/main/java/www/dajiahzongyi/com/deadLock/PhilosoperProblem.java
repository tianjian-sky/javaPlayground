// package demoApp.src.main.java.www.dajiazhongyi.com;

import java.lang.Thread;
import java.util.concurrent.Semaphore;

/**
 * 哲学家问题!
 *
 */
public class PhilosoperProblem {
    public static void main( String[] args )
    {
        Fork [] forks = new Fork[] {
            new Fork(),
            new Fork(),
            new Fork(),
            new Fork(),
            new Fork()
        };
        Thread t0 = new Philosopher(0, forks);
        Thread t1 = new Philosopher(1, forks);
        Thread t2 = new Philosopher(2, forks);
        Thread t3 = new Philosopher(3, forks);
        Thread t4 = new Philosopher(4, forks);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Signal {
    public static final Semaphore LOCK = new Semaphore(4);
}

class Fork {
}

class Philosopher extends Thread {
    private int index;
    private Fork[] forks;
    private long count = 10000000L;
    Philosopher (int index, Fork[] forks) {
        this.index = index;
        this.forks = forks;
    }
    public void run() {
        while (true && count >= 0) {
            count--;
            try {
                Signal.LOCK.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("哲学家" + this.index + ": 正在思考问题。。。");
            synchronized (forks[this.index]) {
                System.out.println("哲学家" + this.index + ": 拿起左叉子。。。");
                synchronized (forks[(this.index + 1) % forks.length]) {
                    System.out.println("哲学家" + this.index + ": 拿起右叉子。。。");
                    System.out.println("哲学家" + this.index + ": 正在吃饭。。。");
                }
                System.out.println("哲学家" + this.index + ": 放下右叉子。。。");
            }
            System.out.println("哲学家" + this.index + ": 放下左叉子。。。");
            Signal.LOCK.release();
        }
    }
}


