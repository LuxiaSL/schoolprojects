import java.util.Arrays;
import java.util.HashMap;

class GenArr {
    public int[] arr;      //the main array

    public void genConsecutive(int bottom, int top, int step){
        //for a consecutive list, top-bottom will be size, plus one for range, divided by the step
        int size = (top-bottom+1)/step;
        int[] genArr = new int[size+1];
        int index = 0;
        //from 0 to the max size
        for(int i = 0; i<size+1; i++){
            //the entry will be equal to the bottom plus iter*step.
            genArr[index] = (bottom + i*step);
            index++;
        }
        this.arr = genArr;
    }
}

class SearchAnalyse {
    public static double analyseSearch(int[] toSearch, int toFind, int selection){
        switch (selection) {
            case 1 -> {
                //tern search
                long start = System.nanoTime();
                TernaryS(toSearch, 0, toSearch.length-1, toFind);
                long end = System.nanoTime();
                return (double)((end - start)) / 1000000;
            }
            case 2 -> {
                //binary search
                long start = System.nanoTime();
                BinaryS(toSearch, 0, toSearch.length-1, toFind);
                long end = System.nanoTime();
                return (double)((end - start)) / 1000000;
            }
            case 3 -> {
                //brute force
                long start = System.nanoTime();
                BruteForceS(toSearch, toFind);
                long end = System.nanoTime();
                return (double)((end - start)) / 1000000;
            }
            default -> { return -1; /*shouldnt occur unless I typo*/ }
        }
    }

    //plain search function to return the search index, not the timing
    public static int doSearch(int[] toSearch, int toFind, int selection){
        switch (selection) {
            case 1 -> {
                //tern search
                return TernaryS(toSearch, 0, toSearch.length-1, toFind);
            }
            case 2 -> {
                //binary search
                return BinaryS(toSearch, 0, toSearch.length-1, toFind);
            }
            case 3 -> {
                //brute force
                return BruteForceS(toSearch, toFind);
            }
            default -> { return -1; /*shouldnt occur unless I typo*/ }
        }
    }

    //all of the following sorts require the array to be presorted (achieved through consecutive generation)

    //basically, split the given array into thirds, then check through each third.
    //is very fast compared to binary for huge arrays, same speed for medium as binary
    private static int TernaryS(int[] arr, int first, int last, int toFind){
        //recursively check through each chunk for the number
        if(last>=first){
            int mid1 = first + (last - first) / 3;
            int mid2 = last - (last - first) / 3;
            if(arr[mid1] == toFind) return mid1;
            if(arr[mid2] == toFind) return mid2;
            if(arr[mid1] > toFind) return TernaryS(arr, first, mid1-1, toFind);
            if(arr[mid2] < toFind) return TernaryS(arr, mid2+1, last, toFind);
            return TernaryS(arr, mid1+1, mid2-1, toFind);
        }
        return -1;
    }

    //split the given array into halves and recursively check for toFind
    //good for medium sized arrays
    private static int BinaryS(int[] arr, int first, int last, int toFind){
        if(last>=first){
            int mid = first + (last - first) / 2;
            if(arr[mid] == toFind) return mid;
            if(arr[mid] > toFind) return BinaryS(arr, first, mid-1, toFind);
            return BinaryS(arr, mid+1, last, toFind);
        }
        return -1;
    }

    //just power through the array from 0-length
    //will find the first few items faster/just as fast as the other methods
    //slows down drastically as the search item is deeper
    private static int BruteForceS(int[] arr, int toFind){
        for(int i = 0; i < arr.length; i++){
            if(arr[i]==toFind){return i;}
        }
        return -1;
    }
}

class Main {
    public static void main(String[] args) {
        //HashMap that looks like results['Search Type'][run][times]
        HashMap<String, double[][]> results = new HashMap<String, double[][]>();
        GenArr toSearch = new GenArr();
        int repeats = 3;

        //extremely large array to check through, step of 3 in order to introduce some 'randomness' to the generation
        toSearch.genConsecutive(1,2000000000,3);

        //for each search type
        for(int j = 1; j < 4; j++){
            //determine name/type of search
            String name = j == 1 ? "Ternary" : j == 2 ? "Binary" : "Brute";

            //starting from 0, add into
            double[][] run = new double[10][repeats];
            //each of the following is a search analysis for the number of repeat runs on the toFind in the second spot
            //the times are recorded into the array, in order to allow for possible variation
            //steps up through each number in the array from the bottom to the top
            //includes a number not found that is outside the larger bound
            for(int i = 0; i<repeats ; i++) {
                run[0][i] = SearchAnalyse.analyseSearch(toSearch.arr, 34, j);
            }
            for(int i = 0; i<repeats ; i++) {
                run[1][i] = SearchAnalyse.analyseSearch(toSearch.arr, 199, j);
            }
            for(int i = 0; i<repeats ; i++) {
                run[2][i] = SearchAnalyse.analyseSearch(toSearch.arr, 1999, j);
            }
            for(int i = 0; i<repeats ; i++) {
                run[3][i] = SearchAnalyse.analyseSearch(toSearch.arr, 19999, j);
            }
            for(int i = 0; i<repeats ; i++) {
                run[4][i] = SearchAnalyse.analyseSearch(toSearch.arr, 199999, j);
            }
            for(int i = 0; i<repeats ; i++) {
                run[5][i] = SearchAnalyse.analyseSearch(toSearch.arr, 1999999, j);
            }
            for(int i = 0; i<repeats ; i++) {
                run[6][i] = SearchAnalyse.analyseSearch(toSearch.arr, 19999999, j);
            }
            for(int i = 0; i<repeats ; i++) {
                run[7][i] = SearchAnalyse.analyseSearch(toSearch.arr, 199999999, j);
            }
            for(int i = 0; i<repeats ; i++) {
                run[8][i] = SearchAnalyse.analyseSearch(toSearch.arr, 1999999999, j);
            }
            for(int i = 0; i<repeats ; i++) {
                run[9][i] = SearchAnalyse.analyseSearch(toSearch.arr, 2000000003, j);
            }
            //place into the table under the respective sort name
            results.put(name, run);
        }

        //once all search types are ran, print the results.
        for(String search : results.keySet()){
            System.out.println(search + " Search Runs");
            System.out.println(Arrays.deepToString(results.get(search)));
        }
    }
}
