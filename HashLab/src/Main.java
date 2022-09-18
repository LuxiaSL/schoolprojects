import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

class IDManage{
    public Hashtable<Integer, String> table = new Hashtable<>();
    final int max = 100000000;
    final int min = 0;

    //generate random between min-max
    //if number exists, generate another.
    private int genNumber(){
        int randID = ThreadLocalRandom.current().nextInt(min, max);
        return requestNumber(randID) ? genNumber() : randID;
    }

    //generate rand then place into table, assigning ID to a given name
    public int getNumber(String name){
        int randID = genNumber();
        this.table.put(randID, name);
        return randID;
    }

    //named function for the class
    public boolean requestNumber(int key){
        return this.table.containsKey(key);
    }
}

public class Main {

    public static void main(String[] args) {
        //create the new idmanager object and array of names to assign ID to
        IDManage manager = new IDManage();
        String[] names = {"James", "Kevin", "Mary", "Louise", "Helen", "Boss", "Steven", "Todd", "Nick", "Harry", "Bob", "Robert", "Chase"};

        //for each name
        for(String name : names){
            //assign and return a unique ID
            int id = manager.getNumber(name);
            //print out the name and corresponding ID
            System.out.println("ID of " + name + ": " + id);
        }

        System.out.println("ID 46464646 Exists (example): " + manager.requestNumber(46464646));
    }
}
