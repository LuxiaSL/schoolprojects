//various useful imports
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

//object for the generation and ensuing methods relating to the one dim array
public class OneDimGen {
    public int[] arr;	//the main array
    private boolean isSorted = false;	//whether the array is sorted
    private boolean sortAsc = false;	//whether the sort is ascending

	//constructor, will generate the array and fill with random numbers.
    public OneDimGen(int size, int min, int max){
        int[] genArr = new int[size];
        for(int i = 0; i<size; i++){
            //the bound is exclusive, need+1 to include the max as a rand
            genArr[i] = ThreadLocalRandom.current().nextInt(min,max+1);
        }
        this.arr = genArr;
    }
	
	//prints the current array
    public void showArr(){ System.out.println(Arrays.toString(this.arr)); }
	
	//finds the mean of the current array
    public double getMean(){
        float sum = 0;
		//for each item in the array, assign it to i, add to sum.
        for(int i : this.arr) {
            sum += i;
        }
		//divide the sum by the length.
        return sum / this.arr.length;
    }

	//prints the mean (special decimal format for rounding)
    public void showMean(){ System.out.println("Mean of current array: " + new DecimalFormat("#.##").format(this.getMean())); }
	
	//finds the maximum
    public int getMax(){
		//if array is currently not sorted, sort it in ascending.
        if(!(isSorted)) this.sortArr(true);
		//return, based on ascension and ensuing index, the maximum value
        return (sortAsc ? this.arr[this.arr.length-1] : this.arr[0]);
    }
	
	//print the max
    public void showMax(){ System.out.println("Maximum of current array: " + this.getMax()); }

	//finds the minimum using the same process as the max, just reverse index
    public int getMin(){
        if(!(isSorted)) this.sortArr(true);
        return (sortAsc ? this.arr[0] : this.arr[this.arr.length-1]);
    }
	
	//print minimum
    public void showMin(){ System.out.println("Minimum of current array: " + this.getMin()); }
	
	//method to sort the array, choosing ascending/descending sort.
    public void sortArr(boolean ascending){
        //if we know it is already sorted, and if we are to reverse the order
        if(isSorted && (sortAsc == !ascending)){
			//reverse the array, mark whether ascending
            reverse(this.arr);
            sortAsc = ascending;
        }else{
			//not sorted, needs to be. mark whether ascending, then sort.
            sortAsc = ascending;
            OneDimSort.sort(this.arr);
			//if it is to be descending, make it so.
            if(!ascending) reverse(this.arr);
        }
		
		isSorted = true;
    }
	
	//reversal method
    private static void reverse(int[] toReverse){
        int length = toReverse.length;
		//for each item in the array, swap the left index with the right index, meet in middle
        for(int i = 0; i < length / 2; i++){
            //perform swap
            swap(toReverse, i, length - i - 1);
        }
    }
	
	//basically, accepts two indexes in an array, and then swaps their values.
    private static void swap(int[] toSwap, int leftIndex, int rightIndex){
        int hold = toSwap[leftIndex];
        toSwap[leftIndex] = toSwap[rightIndex];
        toSwap[rightIndex] = hold;
    }
}
