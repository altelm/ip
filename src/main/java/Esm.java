import java.util.*;
import java.io.*;
public class Esm {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list= new ArrayList<>();
        System.out.println("Hello! I'm Esm\nWhat can I do for you?\n");
        String in = scanner.nextLine();
        while(!in.equalsIgnoreCase("bye")) {
            if(in.equalsIgnoreCase("list")) {
                for(int i = 1; i <= list.size(); i++) {
                    System.out.println( i + ". " + list.get(i-1));
                }
            } else {
                list.add(in);
                System.out.println("added: "+ in);
            }

            in = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again\n");

    }
}
