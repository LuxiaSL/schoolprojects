//useful imports
import java.util.LinkedList;
import java.util.Scanner;

//object for generating the linked list from a given string
public class Rainbow {
    //the big list, no other variables need global here
    public LinkedList<String> list = new LinkedList<>();

    //constructor, will generate the linked list with given color string
    public Rainbow(String colors){
        //pull apart the given string, put it into list
        for(int i = 0;i < colors.length();i++){
            //cast to string
            list.add(colors.charAt(i) + "");
        }
    }

    //display current info on list
    public void showContents(){
        System.out.println("Count: " + list.size() + ", Contents: " + list);
    }

    //insert functions

    //show the prompt for looping inserts
    public void showInsertPrompt(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Insert to the start [1] or end [2]: ");
        String promptRes = scan.next();

        //based on prior prompt, decide whether to insert at start or end, or loop again to get proper input
        if(promptRes.equals("1")){
            insertStart();
        }else if(promptRes.equals("2")){
            insertEnd();
        }else{
            System.out.println("ERR: Not 1 or 2");
            showInsertPrompt();
        }
    }

    //insert an input to the start
    public void insertStart(){
        //temp var
        String insert = "";

        //while loop to continue until proper color is given
        while(true){
            //try to retrieve the color from the user; break once successful
            try{
                insert = this.getColor("Add color to the start: ", true);
                break;
            }catch (Exception e){
                //possible exceptions: color is not char, in alphabet, or already exists
                System.out.println("ERR: " + e.getMessage());
            }
        }

        //once retrieved, add to start
        list.addFirst(insert);
    }

    //insert an input to the end
    public void insertEnd(){
        //temp var
        String insert = "";

        //while loop to continue until proper color is given
        while(true){
            //try to retrieve the color from user; break once successful
            try{
                insert = this.getColor("Add color to the end: ", true);
                break;
            }catch (Exception e){
                //possible exceptions: color is not char, in alphabet, or already exists
                System.out.println("ERR: " + e.getMessage());
            }
        }

        //once retrieved, place onto the end
        list.addLast(insert);
    }
    //end inserts

    //removal function
    public void promptRemove(){
        //firstly, if empty list, you can't remove anything, exit.
        if(list.isEmpty()){
            System.out.println("ERR: Can't remove, empty list.");
            return;
        }

        //temp var
        String toRemove = "";

        //while loop to continue until proper color given
        while(true){
            //try to retrieve color from user; break once successful
            try{
                toRemove = this.getColor("Enter the color to remove: ", false);
                break;
            }catch (Exception e){
                //possible exceptions: color DOESN'T exist, or is somehow not valid otherwise
                System.out.println("ERR: " + e.getMessage());
            }
        }

        //once retrieved, remove from the list
        list.remove(toRemove);
    }

    //etc functions

    //retrieve a color from the user.
    //prompt is what is shown to the user to describe color needed, doInsert signifies whether inserting or removing
    public String getColor(String prompt, boolean doInsert) throws Exception{
        //retrieve input
        Scanner scan = new Scanner(System.in);
        System.out.print(prompt);
        String input = scan.next();

        //if the character is somehow not good (null, not singular, or is not part of alphabet)
        if(!validateColorChar(input)){
            throw new Exception("Not a char. Try again.");
        }
        //if color is to be inserted AND the list already has it
        if(doInsert && list.contains(input.toUpperCase())){
            throw new Exception("Color already exists. Try again.");
        }
        //if color is to be removed AND the list DOES NOT have it
        if(!doInsert && !list.contains(input.toUpperCase())){
            throw new Exception("Color doesn't exist. Try again.");
        }

        //if no priors, good color, return it
        return input.toUpperCase();
    }

    //quick one liner to validate if string is char
    private static boolean validateColorChar(String toCheck){
        return toCheck != null && toCheck.length() == 1 && toCheck.chars().allMatch(Character::isLetter);
    }
    //end etc
}
