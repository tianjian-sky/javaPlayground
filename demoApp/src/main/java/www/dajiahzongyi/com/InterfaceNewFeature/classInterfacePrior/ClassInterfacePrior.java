interface I {
    default void show () {
        System.out.println("A");
    }
    public void nonDefaultShow ();
}

class C {
    public void show(){
        System.out.println("C");
    }
    public void nonDefaultShow () {
        System.out.println("类和接口同名的方法");
    }
}

class A extends C implements I {
    public void nonDefaultShow () {
        System.out.println("实现接口的同名方法");
    }
}

public class ClassInterfacePrior {
    public static void main(String args[]) {
        System.out.println("hahha");
        A ia = new A();
        ia.show();
        ia.nonDefaultShow();
    }
}


