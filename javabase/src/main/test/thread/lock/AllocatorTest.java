package thread.lock;

import lombok.Builder;
import lombok.Getter;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class AllocatorTest {


    @Test
    public void testAccount() throws InterruptedException {
        Account accountA = Account.builder().balance(100.0).build();
        Account accountB = Account.builder().balance(200.0).build();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 100; i ++) {
                try {
                    accountA.transfer(accountB, 0.5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 100; i ++) {
                try {
                    accountB.transfer(accountA, 0.5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(String.format("accountA balance: %f", accountA.getBalance()));
        System.out.println(String.format("accountB balance: %f", accountB.getBalance()));
    }

}


@Builder
@Getter
class Account {


    Double balance;
    Allocator allocator;

    public void transfer(Account target, Double amount) throws InterruptedException {
        allocator = Allocator.getInstance();
        try {
            allocator.apply(this, target);
            if (this.balance > amount) {
                target.balance = target.balance + amount;
                this.balance = this.balance - amount;
            }

            //模拟转账时间
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10));
        } finally {
            allocator.free(this, target);
        }

    }
}

class Allocator {


    public volatile static Allocator allocator = getInstance();

    public static Allocator getInstance()  {
        if (allocator == null) {
            synchronized (Allocator.class) {
                if (allocator == null) {
                    return new Allocator();
                }
            }
        }
        return allocator;

    }


//    Set<Object> locks = new HashSet<>();

    List<Object> locks = new LinkedList<>();

    public synchronized void apply(Object from , Object to) {

        while (locks.contains(from) || locks.contains(to)) {

            try {
                wait();
            } catch (Exception e) {
                System.out.println("error apply");
            }
        }
        locks.add(from);
        locks.add(to);
    }

    public synchronized void free(Object from, Object to) {
        locks.remove(from);
        locks.remove(to);
        notifyAll();
    }



}