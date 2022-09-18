//various useful imports
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

//object for generation and ensuing methods relating to multi dim arrays
public class MultiDimGen {
    public int[][] arr;                 //overarching array
    private final int _rows;            //rows in array
    private final int _columns;         //columns in array
    private boolean isSorted = false;	//whether the array is sorted
    private boolean sortAsc = false;	//whether the sort is ascending

    //constructor, will generate the array and fill with random numbers.
    public MultiDimGen(int rows, int columns, int min, int max){
        int[][] genArr = new int[rows][columns];
        //outermost array
        for(int i = 0; i < rows; i++){
            //innermost
            for(int j = 0; j < columns; j++){
                //the bound is exclusive, need+1 to include the max as a rand
                genArr[i][j] = ThreadLocalRandom.current().nextInt(min,max+1);
            }
        }
        //assign values
        this.arr = genArr;
        _rows = rows;
        _columns = columns;
    }

    //prints the current array
    public void showArr(){ System.out.println(Arrays.deepToString(this.arr)); }
    public void showMatrixForm() {
        //separate arrays with lines
        for (int[] i : this.arr) {
            System.out.println(Arrays.toString(i));
        }
    }

    //print the sum
    public void showSum(){ System.out.println("Sum of the array: " + this.getTotalSum()); }

    //sum of all array elements
    public int getTotalSum(){
        int sum = 0;
        //for each row and value in columns, add them to sum
        for(int[] row : this.arr){
            for(int val : row){
                sum += val;
            }
        }
        return sum;
    }

    //print the minimum value
    public void showMin(){ System.out.println("Minimum of the array: " + this.getMin()); }

    //retrieve the min
    public int getMin(){
        //if not already sorted, sort.
        if(!(isSorted)) this.sortArr(true);
        //once sort is assured, return either the first or last value, based on ascending or no
        return (sortAsc ? this.arr[0][0] : this.arr[_rows-1][_columns-1]);
    }

    //print the maximum value
    public void showMax(){ System.out.println("Maximum of the array: " + this.getMax()); }

    //retrieve the max
    public int getMax(){
        //if not already sorted, sort.
        if(!(isSorted)) this.sortArr(true);
        //once sort is assured, return either the first or last value, based on ascending or no
        return (sortAsc ? this.arr[_rows-1][_columns-1] : this.arr[0][0]);
    }

    //method to sort the array, choosing ascending/descending sort.
    public void sortArr(boolean ascending){
        //if we know it is already sorted, and if we are to reverse the order
        if(isSorted && (sortAsc == !ascending)){
            //reverse the array, mark whether ascending
            reverseMulti(this.arr, _rows, _columns);
            sortAsc = ascending;
        }else{
            //not sorted, needs to be. mark whether ascending, then sort.
            sortAsc = ascending;
            this.arr = MultiDimSort.sort(this.arr, _rows, _columns);
            //if it is to be descending, make it so.
            if(!ascending) reverseMulti(this.arr, _rows, _columns);
        }

        //assign value that it is now sorted
        isSorted = true;
    }

    //display the odds within the multi dim array
    public void showOdds(){
        //big string to append to
        StringBuilder odds = new StringBuilder();
        //iterator
        int rowCount = 0;
        for (int[] row : this.arr) {
            //iterator
            int colCount = 0;
            for(int val : row){
                //iterate over each value in the array. if odd (num%2 is 1 if odd), append value and pos to string
                if(val%2==1) {
                    odds.append("(");
                    odds.append(val);
                    odds.append(", pos: [");
                    odds.append(rowCount);
                    odds.append("][");
                    odds.append(colCount);
                    odds.append("]) ");
                }
                colCount++;
            }
            rowCount++;
        }
        //output
        System.out.println("Odds are as follows: ");
        System.out.println(odds);
    }

    //multi reversal method
    private static void reverseMulti(int[][] toReverse, int rows, int columns){
        //when reversing, the array is guaranteed to be sorted.
        for(int i = 0; i < rows / 2; i++){
            //starting with top left and bottom right corners, swap and traverse until the middle is met.
            for(int j = 0; j < columns; j++){
                multiSwap(toReverse, i, j, rows-i-1, columns-j-1);
            }
        }
        //case check, if odd # of rows, flip the middle one's contents (middle will be rows / 2 + 1)
        if(rows%2==1){
            int middleRow = rows/2;
            for(int j = 0; j < columns / 2; j++){
                swap(toReverse[middleRow], j, columns-j-1);
            }
        }
    }

    //accepts coordinates for swapping
    private static void multiSwap(int[][] toSwap, int lRow, int lColumn, int rRow, int rColumn){
        int hold = toSwap[lRow][lColumn];
        toSwap[lRow][lColumn] = toSwap[rRow][rColumn];
        toSwap[rRow][rColumn] = hold;
    }

    //basically, accepts two indexes in an array, and then swaps their values.
    private static void swap(int[] toSwap, int leftIndex, int rightIndex){
        int hold = toSwap[leftIndex];
        toSwap[leftIndex] = toSwap[rightIndex];
        toSwap[rightIndex] = hold;
    }
}