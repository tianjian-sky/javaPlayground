public class ProducerConsumer {
    public static void main(String args[]) {
        Repo rep = new Repo();
        Thread p1 = new Producer(rep, "p1");
        Thread c1 = new Consumer(rep, "c1");
        Thread p2 = new Producer(rep, "p2");
        Thread c2 = new Consumer(rep, "c2");
        Thread p3 = new Producer(rep, "p3");
        Thread c3 = new Consumer(rep, "c3");
        p1.start();
        c1.start();
        p2.start();
        c2.start();
        p3.start();
        c3.start();
    }
}

class Consumer extends Thread{
    public String name;
    private Repo rep;
    Consumer(Repo rep, String name) {
        this.rep = rep;
        this.setName(name);
    }
    public void run () {
        Long count = 100L;
        int data = 0;
        while (count-- >= 0) {
            try {
                Thread.sleep((int) Math.random() * 1000);
            } catch (Exception e) {
                //TODO: handle exception
            }
            rep.get();
        }
    }
}

class Producer extends Thread {
    public String name;
    private Repo rep;
    Producer(Repo rep, String name) {
        this.rep = rep;
        this.setName(name);
    }
    public void run () {
        Long count = 100L;
        int data = 0;
        while (count-- >= 0) {
            try {
                Thread.sleep((int) Math.random() * 1000);
            } catch (Exception e) {
                //TODO: handle exception
            }
            rep.put();
        }
    }
}

class Repo {
    private Object tempLock = new Object();
    private int[] rack = new int[10];
    private int putIndex = 0;
    private int getIndex = 0;
    private int dataCount = 0;
    private int data = 0;
    public void put () {
        synchronized(this.tempLock) {
        
            while (dataCount == rack.length) {
            // if (dataCount == rack.length) {
                try {
                    // this.wait(); // IllegalMonitorStateException
                    this.tempLock.wait();
                } catch (InterruptedException e) {
                    //TODO: handle exception
                }
                
            }
            rack[putIndex] = data++;
            System.out.println("生产者" + Thread.currentThread().getName() + "向货架【"+ putIndex + "]位置放入货物：" + data);
            putIndex = (putIndex+1) % rack.length;
            dataCount++;
            this.tempLock.notify();
        }
    }

    public void get() {
        synchronized(this.tempLock) {
            while (dataCount == 0) {
            // if (dataCount == 0) 此处不能用if，因为消费者消费完后会做一次notify，被阻塞的另外一个消费者线程如果写在if里，在被唤醒后就直接向后做取空操作了
                try {
                    this.tempLock.wait();
                } catch (InterruptedException e) {
                    //TODO: handle exception
                }
                
            }
            int pro = rack[getIndex];
            System.out.println("\t\t消费者" + Thread.currentThread().getName() + "向货架【"+ getIndex + "]位置取出货物：" + pro);
            getIndex = (getIndex+1) % rack.length;
            dataCount--;
            this.tempLock.notify();
        }
    }
}
