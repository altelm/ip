import java.util.*;
import java.io.*;
public class Esm {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list= new ArrayList<>();
        System.out.println("Hello! I'm Esm\nWhat can I do for you?\n");
        String temp = scanner.nextLine();
        String[] in = temp.split("\\s+");
        while(!in[0].equalsIgnoreCase("bye")) {

            if(in[0].contains("mark")) {
                int num  = Integer.parseInt(in[1]);
                if( in[0].equalsIgnoreCase("mark")) {
                    list.get(num - 1).setDone("X");
                    System.out.println("I've marked this task as done:)\n["+list.get(num-1).getDone()+"] "+list.get(num-1).getName());
                } else {
                    list.get(num-1).setDone("");
                    System.out.println("I've marked this task as not done:(:)\n["+list.get(num-1).getDone()+"] "+list.get(num-1).getName());
                }
            } else if(in[0].equalsIgnoreCase("list")) {
                for(int i = 1; i <= list.size(); i++) {
                    System.out.println( i + ".[" + list.get(i-1).getDone() + "] " + list.get(i-1).getName());
                }
            } else {
                list.add(new Task(temp, ""));
                System.out.println("added: "+ temp);
            }

            temp = scanner.nextLine().trim();
            in = temp.split("\\s+");
        }
        System.out.println("Bye. Hope to see you again\n");

    }
}
