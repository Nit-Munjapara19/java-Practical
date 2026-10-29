import java.util.Scanner;

class Cinema {
    int seatsLeft;

    Cinema(int seats) {
        seatsLeft = seats;
    }

    void book(String name) {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            seatsLeft--;
            System.out.println(name + " booked a seat.");
        } else {
            System.out.println(name + " failed. No seat available.");
        }
    }

    synchronized void synchronizedBook(String name) {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            seatsLeft--;
            System.out.println(name + " booked a seat.");
        } else {
            System.out.println(name + " failed. No seat available.");
        }
    }
}

public class SeatBookingRace {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of seats: ");
        int seats = sc.nextInt();

        System.out.print("Enter number of booking threads: ");
        int threads = sc.nextInt();

        Cinema c1 = new Cinema(seats);
        Thread[] t = new Thread[threads];

        System.out.println("\n--- Without Synchronization ---");

        for (int i = 0; i < threads; i++) {
            final int id = i + 1;

            t[i] = new Thread(() -> {
                c1.book("User-" + id);
            });

            t[i].start();
        }

        for (Thread thread : t) {
            thread.join();
        }

        System.out.println("Seats left: " + c1.seatsLeft);

        Cinema c2 = new Cinema(seats);

        System.out.println("\n--- With Synchronization ---");

        for (int i = 0; i < threads; i++) {
            final int id = i + 1;

            t[i] = new Thread(() -> {
                c2.synchronizedBook("User-" + id);
            });

            t[i].start();
        }

        for (Thread thread : t) {
            thread.join();
        }

        System.out.println("Seats left: " + c2.seatsLeft);

        sc.close();
    }
}