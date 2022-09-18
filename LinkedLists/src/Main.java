import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //set variables
        String colors = "ROYGBIV";              //string with the colors
        Rainbow rainbow = new Rainbow(colors);  //rainbow object

        //step 3, display contents and length
        rainbow.showContents();
        separate();

        //step 4, prompt for and insert color to start. show contents after
        rainbow.insertStart();
        rainbow.showContents();
        separate();

        //step 5, prompt for and insert color to end. show contents after
        rainbow.insertEnd();
        rainbow.showContents();
        separate();

        //step 6, prompt for and remove given color. show contents after
        rainbow.promptRemove();
        rainbow.showContents();
        separate();

        //step 7-x, looping prompt to continue removing/adding colors
        String loopPrompt = "Would you like to remove [R] or add [A] another item? ([E] to exit): ";
        String loopInput = getInput(loopPrompt);
        //when the input isn't E (exit)
        while(!loopInput.equals("E")){
            //switch over the values
            //r->remove, a->insert, anything else here-> incorrect input, retry
            switch (loopInput) {
                case "R" -> rainbow.promptRemove();
                case "A" -> rainbow.showInsertPrompt();
                default -> System.out.println("ERR: Incorrect input");
            }
            //show contents after this operation, loop again
            separate();
            rainbow.showContents();
            loopInput = getInput(loopPrompt);
        }

        //final display of list, exit after.
        separate();
        System.out.println("Final Results");
        rainbow.showContents();
    }

    //prompt for and return input
    private static String getInput(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.print(prompt);
        return scan.next().toUpperCase();
    }

    //output a bar to format nicer
    private static void separate(){
        System.out.println("------------------------------------------------");
    }
}
