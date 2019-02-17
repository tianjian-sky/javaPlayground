import java.util.concurrent.Semaphore;

/**
 * 
 * 用信号量代替synchronized ,notify, wait
 * 实现生产者消费者问题
 * 
 */

interface InterfaceA {
    public void funA (String a);
    public void funB (String a, int b);
}
interface InterfaceB {
    public default void funA (String a) {
        System.out.println("我是默认接口方法A");
    };
    public void funB (String a, int b);
}

class InterfaceImpl implements InterfaceB { // 1. 接口默认方法
    public void funB (String a, int b) {
        System.out.println("接口B默认方法B");
    }
}

class InterfaceImpl2 implements InterfaceA, InterfaceB { // 1. 被实现的两个接口都包含funA，且funA至少在一个类中是默认方法，则在实现类中必须实现
    public void funA (String a) {
        System.out.println("我是默认接口方法A的实现方法");
    };
    public void funB (String a, int b) {
        System.out.println("接口B默认方法B");
    }
}

public class DefaultMethodsDemo {
    public static void main(String args[]) {
        System.out.println("hahha");
        InterfaceImpl ia = new InterfaceImpl();
        ia.funA("a");
        ia.funB("b", 0);
        InterfaceImpl2 ib = new InterfaceImpl2();
        ib.funA("a");
        ib.funB("b", 0);
    }
}


