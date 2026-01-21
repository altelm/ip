import java.util.*;
import java.io.*;
public class Esm {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Chat\nWhat can I do for you?\n");
        String in = scanner.nextLine();
        while(!in.equalsIgnoreCase("bye")) {
            System.out.println(in);
            in = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again\n");

    }
}
