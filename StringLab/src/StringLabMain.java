import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    //prompt for and return input
    private static String getInput(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.print(prompt);
        return scan.next();
    }

    //count up the vowels in a given string
    private static int countVowels(String givenString){
        String allVowels = "aeiou";
        int count = 0;
        //convert to lower
        for(char ch: givenString.toLowerCase().toCharArray()){
            //if its a space, don't bother checking and continue
            if(ch == ' ') continue;
            //if ch appears as an index of aeiou, up the count.
            if(allVowels.indexOf(ch) != -1){ count++; }
        }
        return count;
    }

    //count the consonants in the given string (all not aeiou)
    private static int countConsonant(String givenString){
        String allVowels = "aeiou";
        int count = 0;
        //convert to lower
        for(char ch: givenString.toLowerCase().toCharArray()){
            //if its a space, don't bother checking and continue
            if(ch == ' ') continue;
            //if ch DOESN'T appear as an index of aeiou, up the count.
            if(allVowels.indexOf(ch) == -1){ count++; }

        }
        return count;
    }

    //print the position of the given word in the string
    private static void printWordPos(String toFind,String toSearch){
        int init = toSearch.indexOf(toFind);
        int end = init + toFind.length();
        System.out.println("Position of '" + toFind + "': Start [" + init + "], End [" + end + "]");
    }

    //output a bar to format nicer
    private static void separate(){
        System.out.println("------------------------------------------------");
    }

    public static void main(String[] args) {
        //first/last names
        String fName, lName, fullName;

        //retrieve
        fName = getInput("Please enter your first name: ");
        lName = getInput("Please enter your last name: ");
        separate();

        //print to screen
        fullName = fName + " " + lName;
        //take out the space from the length
        System.out.println("Hello " + fullName + ". Length: " + (fullName.length() - 1));
        separate();

        //turn to upper/lower and display
        System.out.println("Upper: " + fullName.toUpperCase());
        System.out.println("Lower: " + fullName.toLowerCase());
        separate();

        //count vowels, display
        int vowels = countVowels(fullName);
        int consonant = countConsonant(fullName);
        System.out.println("Vowels: " + vowels + ", Consonants: " + consonant);
        separate();

        //store text, and perform operations
        String searchText = "I am a very good student who works hard";
        //position 10... 10 index or the 10th char? assumed 10 index.
        System.out.println("Char @ 10: " + searchText.charAt(10));
        printWordPos("good", searchText);
        separate();

        //looping prompt for "Excellent"
        String loopPrompt = "Please enter the word 'Excellent' ('E' to stop loop): ";
        String input = getInput(loopPrompt);
        while(!input.equals("Excellent")){
            if(input.equals("E")){ System.exit(0); }
            System.out.println("ERR: Input does not match. Please try again.");
            separate();
            input = getInput(loopPrompt);
        }
        separate();

        //print date & time
        System.out.println("Current Date and Time : ");
        System.out.println(DateTimeFormatter.ofPattern("LLLL dd, yyyy").format(LocalDateTime.now()));
        System.out.println(DateTimeFormatter.ofPattern("h:mm a").format(LocalDateTime.now()));
        separate();
    }
}
