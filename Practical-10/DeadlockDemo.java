import java.util.Scanner;

public class DeadlockDemo {

    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    static void createDeadlock() {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 locked Lock 1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                synchronized (lock2) {
                    System.out.println("Thread 1 locked Lock 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 locked Lock 2");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                synchronized (lock1) {
                    System.out.println("Thread 2 locked Lock 1");
                }
            }
        });

        t1.start();
        t2.start();

        System.out.println("Deadlock created: both threads may wait forever.");
    }

    static void avoidDeadlock() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                synchronized (lock2) {
                    System.out.println("Thread 1 completed.");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock1) {
                synchronized (lock2) {
                    System.out.println("Thread 2 completed.");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("No deadlock. Both threads used the same lock order.");
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Create Deadlock");
        System.out.println("2. Avoid Deadlock");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();

        if (choice == 1) {
            createDeadlock();
        } else if (choice == 2) {
            avoidDeadlock();
        } else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }
}