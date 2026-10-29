import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Buffer {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;

    Buffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }

        queue.add(value);
        System.out.println("Produced: " + value);

        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        int value = queue.remove();
        System.out.println("Consumed: " + value);

        notify();
        return value;
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter buffer size: ");
        int capacity = sc.nextInt();

        System.out.print("Enter number of items: ");
        int items = sc.nextInt();

        Buffer buffer = new Buffer(capacity);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= items; i++) {
                    buffer.produce(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= items; i++) {
                    buffer.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items produced and consumed.");
        sc.close();
    }
}