import java.util.*;
import java.io.*;
public class Esm {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list= new ArrayList<>();
        System.out.println("Greetings I am thy humble Esm\nSpeak and I shall head the!\n");
        String in = scanner.next();

        while(!in.equalsIgnoreCase("bye")) {

                if(in.contains("mark")) {
                    int num  = scanner.nextInt();
                    if( in.equalsIgnoreCase("mark")) {
                        list.get(num - 1).setDone("X");
                        System.out.println("Lo, I have set this task down as finished:)\n"+list.get(num-1));
                    } else {
                        list.get(num-1).setDone("");
                        System.out.println("It is undone, and so marked in thy ledger:(\n" + list.get(num-1));
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
                    } else {
                        System.out.println("I confess myself ignorant of thy intent");
                        continue;
                    }

                    if(name.equals("")) {
                        System.out.println("Words are required, lest thy task vanish into nothingness.");
                        continue;
                    }


                    list.add(temptask);
                    System.out.println("Aye tis done.\n" + temptask + "\nThy ledger now holdeth " + list.size() + " task(s)");
                }


                in = scanner.next();

        }
        System.out.println("Fare thee well\n");

    }


}
