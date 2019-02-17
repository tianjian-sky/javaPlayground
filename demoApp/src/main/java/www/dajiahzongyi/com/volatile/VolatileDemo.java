public class VolatileDemo {
    public static volatile int shareValue = 1;
    public static void main(String args[]) {
        MyThread t1 = new MyThread("张三丰", 1);
        MyThread t2 = new MyThread("王老二", 7);
        t1.start();
        t2.start();
    }
}

class MyThread extends Thread {
    int setValue;
    MyThread (String name, int value) {
        this.setName(name);
        setValue = value;
    }
    public void run () {
        VolatileDemo.shareValue += setValue;
        System.out.println(Thread.currentThread().getName() + ":" + VolatileDemo.shareValue);
    }
}
