import java.util.*;
import java.io.*;
public class Esm {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list= new ArrayList<>();
        System.out.println("Hello! I'm Esm\nWhat can I do for you?\n");
        String in = scanner.next();
        //String[] in = temp.split("\\s+");
        while(!in.equalsIgnoreCase("bye")) {

            if(in.contains("mark")) {
                int num  = scanner.nextInt();
                if( in.equalsIgnoreCase("mark")) {
                    list.get(num - 1).setDone("X");
                    System.out.println("I've marked this task as done:)\n"+list.get(num-1));
                } else {
                    list.get(num-1).setDone("");
                    System.out.println("I've marked this task as not done:(:)\n" + list.get(num-1));
                }
            } else if(in.equalsIgnoreCase("list")) {
                for(int i = 1; i <= list.size(); i++) {
                    System.out.println( i + ". " + list.get(i-1));
                }
            } else {
                String name = scanner.nextLine();
                Task temptask = null;
                if(in.equals("todo")) {
                    temptask= new ToDo(name);
                } else if (in.equals("deadline")) {
                    temptask = new Deadline(name.split("/"));
                } else if (in.equals("event")) {
                    temptask = new Event(name.split("/"));
                }
                list.add(temptask);
                System.out.println("Task added.\n" + temptask + "\nNow there are " + list.size() + " task(s) in list");
            }


            in = scanner.next();
        }
        System.out.println("Bye. Hope to see you again\n");

    }
}
