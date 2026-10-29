import java.util.Scanner;

class Counter {
    int count = 0;

    void increment() {
        count++;
    }

    synchronized void synchronizedIncrement() {
        count++;
    }
}

public class CounterRace {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of threads: ");
        int threads = sc.nextInt();

        System.out.print("Enter increments per thread: ");
        int increments = sc.nextInt();

        Counter c1 = new Counter();

        Thread[] t = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            t[i] = new Thread(() -> {
                for (int j = 0; j < increments; j++) {
                    c1.increment();
                }
            });
            t[i].start();
        }

        for (Thread thread : t) {
            thread.join();
        }

        int expected = threads * increments;

        System.out.println("\nWithout synchronization:");
        System.out.println("Expected count: " + expected);
        System.out.println("Actual count:   " + c1.count);

        Counter c2 = new Counter();

        for (int i = 0; i < threads; i++) {
            t[i] = new Thread(() -> {
                for (int j = 0; j < increments; j++) {
                    c2.synchronizedIncrement();
                }
            });
            t[i].start();
        }

        for (Thread thread : t) {
            thread.join();
        }

        System.out.println("\nWith synchronization:");
        System.out.println("Expected count: " + expected);
        System.out.println("Actual count:   " + c2.count);

        sc.close();
    }
}