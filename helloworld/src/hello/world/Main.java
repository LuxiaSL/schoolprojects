package hello.world;

//Would like to know if I can import from java.time overall, or if not, do I just deal with long code lines in JAVA?
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        //is there an easy way to clear the console screen?

        //print hello world
	    System.out.println("~~ Hello World ~~");
	    //generate the timestamp using java.time, then print it in a message.
        String timestamp = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a").format(LocalDateTime.now());
        System.out.println("System Timestamp: " + timestamp);
        //print welcome message
        String msg = "If words are the language of the people, and mathematics is the language of the universe,"
                    + " then programming is truly an art form.";
        System.out.println(msg);
    }
}
