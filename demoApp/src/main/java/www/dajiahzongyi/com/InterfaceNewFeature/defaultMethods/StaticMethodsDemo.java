import java.util.concurrent.Semaphore;

interface InterfaceA {
    public void funA (String a);
    public void funB (String a, int b);
}
interface InterfaceB {
    static void funA (String a) {
        System.out.println("我是静态接口方法A");
    };
}

class InterfaceImpl implements InterfaceB { // 1. 接口默认方法
    static void funA (String a) {
        System.out.println("我是override的静态接口方法A");
    }
}

public class StaticMethodsDemo {
    public static void main(String args[]) {
        System.out.println("hahha");
        InterfaceImpl ia = new InterfaceImpl();
        InterfaceB.funA("a"); 
        InterfaceImpl.funA("a"); 
        // ia.funA("b");
    }
}


