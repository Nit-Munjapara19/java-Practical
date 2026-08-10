import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter template: ");
        String template = sc.nextLine();

        System.out.print("Enter number of placeholders: ");
        int n = sc.nextInt();

        String[] names = new String[n];
        String[] values = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter placeholder name: ");
            names[i] = sc.next();

            System.out.print("Enter value: ");
            values[i] = sc.next();
        }

        String result = TemplateFiller.fillTemplate(template, names, values);

        System.out.println("Filled template: " + result);

        sc.close();
    }
}