import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

//custom array class
class GenArr {
    public int[] arr;                   //the main array

    public void genRand(int size, int min, int max){
        int[] genArr = new int[size];
        for(int i = 0; i<size; i++){
            //the bound is exclusive, need+1 to include the max as a rand
            genArr[i] = ThreadLocalRandom.current().nextInt(min,max+1);
        }
        this.arr = genArr;
    }

    public void genConsecutive(int bottom, int top, boolean ascend){
        //for a consecutive list, top-bottom will be size, plus one for if same number.
        int[] genArr = new int[top-bottom+1];
        //from 0 to the max size
        for(int i = 0; i<(top-bottom+1); i++){
            //the entry will be equal to the bottom plus iter if ascending, top minus iter if descending.
            genArr[i] = ascend ? (bottom + i) : (top - i);
        }
        this.arr = genArr;
    }

    public void genRepeated(int size, int num){
        //for repeated list of number of determined size
        int[] genArr = new int[size];
        for(int i = 0; i < size; i++){
            genArr[i] = num;
        }
        this.arr = genArr;
    }
}

public class Main {
    public static void main(String[] args) {
        //HashMap that looks like results['Sort Type'][run][times]
        HashMap<String, double[][]> results = new HashMap<String, double[][]>();
        //run each test 10 times
        int repeats = 10;
        GenArr generated = new GenArr();

        //big loop: do all tests on a given sort method first, then continue
        for(int j = 1; j < 4; j++){
            //determine name/type of sort
            String name = j == 1 ? "Insert" : j == 2 ? "Merge" : "Quick";

            //starting from 0, add into
            double[][] run = new double[8][repeats];

            //ADDED AFTER: SMALLER SORT OF 1k AND 2.5k TO TEST
            for(int i = 0; i<repeats ; i++) {
                //every single sort regenerate array
                generated.genRand(1000, 1, 10000);
                run[6][i] = Complexity.analyseSort(generated.arr, j);
            }

            for(int i = 0; i<repeats ; i++) {
                //every single sort regenerate array
                generated.genRand(2500, 1, 10000);
                run[7][i] = Complexity.analyseSort(generated.arr, j);
            }

            //first is the 5k rand from 1-10000
            for(int i = 0; i<repeats ; i++) {
                //every single sort regenerate array
                generated.genRand(5000, 1, 10000);
                run[0][i] = Complexity.analyseSort(generated.arr, j);
            }

            //next is the 10k rand from 1-10000
            for(int i = 0; i<repeats ; i++) {
                generated.genRand(10000, 1, 10000);
                run[1][i] = Complexity.analyseSort(generated.arr, j);
            }

            //finally do the 17.5k rand from 1-10000
            //20k made a stack overflow occur in my quicksort
            for(int i = 0; i<repeats ; i++) {
                generated.genRand(17500, 1, 10000);
                run[2][i] = Complexity.analyseSort(generated.arr, j);
            }

            //etc performance tests, do asc/desc consecutive 1-10000, then repeated.
            //Fourth Run: Ascending Consecutive 1-10000
            for(int i = 0; i<repeats ; i++) {
                //every single sort regenerate array
                generated.genConsecutive(1,10000,true);
                run[3][i] = Complexity.analyseSort(generated.arr, j);
            }

            //Fifth Run: Descending Consecutive 1-10000
            for(int i = 0; i<repeats ; i++) {
                //every single sort regenerate array
                generated.genConsecutive(1,10000,false);
                run[4][i] = Complexity.analyseSort(generated.arr, j);
            }

            //Sixth Run: 10000 Repeated Numbers
            for(int i = 0; i<repeats ; i++) {
                //every single sort regenerate array
                //this one, choose a random num 1-99 to repeat in the array
                generated.genRepeated(10000, ThreadLocalRandom.current().nextInt(0,100));
                run[5][i] = Complexity.analyseSort(generated.arr, j);
            }

            //finally, runs are done. put into the hashmap with the sort name and the runs array
            results.put(name, run);
        }

        //once all sort types are ran, print the results.
        for(String sort : results.keySet()){
            System.out.println(sort + "Sort Runs");
            System.out.println(Arrays.deepToString(results.get(sort)));
        }
    }
}


