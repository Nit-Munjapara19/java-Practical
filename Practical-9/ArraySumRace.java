import java.util.Scanner;

class SharedTotal {
    long total = 0;

    void add(long value) {
        total += value;
    }

    synchronized void synchronizedAdd(long value) {
        total += value;
    }
}

public class ArraySumRace {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int size = sc.nextInt();

        System.out.print("Enter number of threads: ");
        int threads = sc.nextInt();

        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }

        long expected = ((long) size * (size + 1)) / 2;

        System.out.println("\nExpected sum: " + expected);

        // ---------------------------------
        // Method 1: Without synchronization
        // ---------------------------------

        SharedTotal total1 = new SharedTotal();
        Thread[] t = new Thread[threads];

        int chunk = size / threads;

        long start1 = System.nanoTime();

        for (int i = 0; i < threads; i++) {
            final int start = i * chunk;
            final int end = (i == threads - 1) ? size : start + chunk;

            t[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    total1.add(arr[j]);
                }
            });

            t[i].start();
        }

        for (Thread thread : t) {
            thread.join();
        }

        long end1 = System.nanoTime();

        System.out.println("\nWithout synchronization:");
        System.out.println("Sum: " + total1.total);
        System.out.println("Time: " + (end1 - start1) / 1_000_000.0 + " ms");

        // ---------------------------------
        // Method 2: synchronized
        // ---------------------------------

        SharedTotal total2 = new SharedTotal();

        long start2 = System.nanoTime();

        for (int i = 0; i < threads; i++) {
            final int start = i * chunk;
            final int end = (i == threads - 1) ? size : start + chunk;

            t[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    total2.synchronizedAdd(arr[j]);
                }
            });

            t[i].start();
        }

        for (Thread thread : t) {
            thread.join();
        }

        long end2 = System.nanoTime();

        System.out.println("\nWith synchronized:");
        System.out.println("Sum: " + total2.total);
        System.out.println("Time: " + (end2 - start2) / 1_000_000.0 + " ms");

        // ---------------------------------
        // Method 3: Local partial sums
        // ---------------------------------

        long[] partial = new long[threads];

        long start3 = System.nanoTime();

        for (int i = 0; i < threads; i++) {
            final int index = i;
            final int start = i * chunk;
            final int end = (i == threads - 1) ? size : start + chunk;

            t[i] = new Thread(() -> {
                long sum = 0;

                for (int j = start; j < end; j++) {
                    sum += arr[j];
                }

                partial[index] = sum;
            });

            t[i].start();
        }

        for (Thread thread : t) {
            thread.join();
        }

        long finalSum = 0;

        for (long value : partial) {
            finalSum += value;
        }

        long end3 = System.nanoTime();

        System.out.println("\nUsing local partial sums:");
        System.out.println("Sum: " + finalSum);
        System.out.println("Time: " + (end3 - start3) / 1_000_000.0 + " ms");

        sc.close();
    }
}