class Box<T> {
    private T value;

    void set(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }
}

class Pair<A, B> {
    A first;
    B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
}

public class GenericDemo {
    static <T> T first(T[] array) {
        return array[0];
    }

    public static void main(String[] args) {
        Box<Integer> b1 = new Box<>();
        b1.set(100);
        System.out.println(b1.get());

        Box<String> b2 = new Box<>();
        b2.set("Hello");
        System.out.println(b2.get());

        Pair<String, Integer> p = new Pair<>("Rahul", 20);
        System.out.println(p.first + " " + p.second);

        String[] names = {"Amit", "Rahul", "Priya"};
        System.out.println(first(names));
    }
}