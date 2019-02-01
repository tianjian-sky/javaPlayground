public class ProducerConsumer {
    public static void main(String args[]) {
        Repo rep = new Repo();
        Thread p1 = new Producer(rep, "p1");
        Thread c1 = new Consumer(rep, "c1");
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
            rep.put(data++);
        }
    }
}

class Repo {
    private int[] rack = new int[10];
    private int putIndex = 0;
    private int getIndex = 0;
    private int dataCount = 0;
    public synchronized void put (int data) {
        if (dataCount == rack.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
            
        }
        rack[putIndex] = data;
        System.out.println("生产者" + Thread.currentThread().getName() + "向货架【"+ putIndex + "]位置放入货物：" + data);
        putIndex = (putIndex+1) % rack.length;
        dataCount++;
        this.notify();
    }

    public synchronized void get() {
        if (dataCount == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
            
        }
        int pro = rack[getIndex];
        System.out.println("\t\t消费者" + Thread.currentThread().getName() + "向货架【"+ getIndex + "]位置取出货物：" + pro);
        getIndex = (getIndex+1) % rack.length;
        dataCount--;
        this.notify();
    }
}