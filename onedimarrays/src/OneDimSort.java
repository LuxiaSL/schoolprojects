public class OneDimSort {
    //basic quicksort algorithm.
    //using a beginning pivot point from the array, break it into two halves to perform swaps.
    private static void quickSort(int[] toSort, int leftIndex, int rightIndex){
        //they have met in the middle, sort is done.
        if (rightIndex <= leftIndex) return;
        int pivot = toSort[leftIndex];
        //starting sort index is the first index + 1, since pivot is used for first index.
        int i = leftIndex + 1;

        //ii is the partition index, inner loop.
        //starting from the left index, and ending at the upper bound or right index.
        for(int ii = leftIndex + 1; ii <= rightIndex; ii++){
            //if the pivot is greater than the current value in the array...
            if(pivot > toSort[ii]){
                //swap the pivot and current value. increase the starting point.
                swap(toSort, ii, i);
                i++;
            }
        }

        //swap pivot to proper position, from the left index.
        toSort[leftIndex] = toSort[i-1];
        toSort[i-1] = pivot;

        //now quicksort on both sides of the pivot.
        quickSort(toSort, leftIndex, i-2); //the left side of this sort (upper bound is the pivot)
        quickSort(toSort, i, rightIndex); //the right side of this sort (lower bound is the pivot)
    }

    //basically, accepts two indexes in an array, and then swaps their values.
    private static void swap(int[] toSwap, int leftIndex, int rightIndex){
        int hold = toSwap[leftIndex];
        toSwap[leftIndex] = toSwap[rightIndex];
        toSwap[rightIndex] = hold;
    }
	
	//calling method, used in main.
    public static void sort(int[] toSort){
        int size = toSort.length;
        quickSort(toSort, 0, size-1);
    }
}
