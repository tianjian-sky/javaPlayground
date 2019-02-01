
import java.lang.Thread;

public class MultiThreadRunSync {
    public static void main(String args[]) {
        Account acc = new Account(1000);
        ATMThread atm = new ATMThread(acc);
        EPayThread epay = new EPayThread(acc);
        atm.start();
        epay.start();
    }

}

class Account {
    private double balance;
    private Object lock = new Object();
    public Account (double amount) {
        this.balance = amount;
    }

    public synchronized double withdraw (double amount) {
        // synchronized (this) { 同步块 && 同步方法
            System.out.println(amount + ":" + this.balance + ":" + "withdraw");
            if (amount<= 0 || amount > this.balance) {
                return -1;
            } else {
                try {
                    Thread.sleep(3000);  
                } catch (Exception e) {
                }
                this.balance -= amount;
                return amount;
            }
        // }
    }
    public synchronized double epay (double amount) {
        // synchronized (this) { 同步块 && 同步方法
            System.out.println(amount + ":" + this.balance + ":" + "epay");
            if (amount<= 0 || amount > this.balance) {
                return -1;
            } else {
                try {
                    Thread.sleep(3000);  
                } catch (Exception e) {
                }
                this.balance -= amount;
                return amount;
            }
        // }
    }
}

class ATMThread extends Thread {
    Account acc;
    ATMThread (Account acc) {
        this.acc = acc;
    }
    public void run () {
       int result = (int) acc.withdraw(800);
       if (result == -1) {
           System.out.println("取款失败");
       } else {
            System.out.println("ATM取款成功：" + result);
       }
    }
}

class EPayThread extends Thread {
    Account acc;
    EPayThread (Account acc) {
        this.acc = acc;
    }
    public void run () {
        int result = (int) acc.epay(800);
        if (result == -1) {
            System.out.println("网银支付失败");
        } else {
            System.out.println("网银支付成功：" + result);
        }
    }


}
