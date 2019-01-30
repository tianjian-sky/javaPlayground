
import java.lang.Thread;

public class MultiThreadRun {
    public static void main(String args[]) {
        Account acc = new Account(800);
        ATMThread atm = new ATMThread(acc);
        EPayThread epay = new EPayThread(acc);
        atm.start();
        epay.start();
    }

}

class Account {
    private double balance;
    public Account (double amount) {
        this.balance = amount;
    }

    public double withdraw (double amount) {
        if (amount<= 0 || amount > this.balance) {
            return -1;
        } else {
            this.balance -= amount;
            return amount;
        }
    }
    public double epay (double amount) {
        if (amount<= 0 || amount > this.balance) {
            return -1;
        } else {
            this.balance -= amount;
            return amount;
        }
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
