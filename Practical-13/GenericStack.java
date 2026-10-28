class Stack<T> {
    private Object[] data;
    private int top = -1;

    Stack(int size) {
        data = new Object[size];
    }

    void push(T value) {
        if (top == data.length - 1) {
            System.out.println("Stack is full");
            return;
        }
        data[++top] = value;
    }

    @SuppressWarnings("unchecked")
    T pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        }
        T value = (T) data[top];
        data[top--] = null;
        return value;
    }
}

public class GenericStack {
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>(5);
        s1.push(10);
        s1.push(20);
        System.out.println(s1.pop());

        Stack<String> s2 = new Stack<>(5);
        s2.push("Hello");
        s2.push("Java");
        System.out.println(s2.pop());
    }
}