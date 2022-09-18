public class MultiDimSort {
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

    //convert the multidim array into a one dimensional in order to quicksort
    private static int[] toOneDim(int[][] toConvert, int rows, int columns){
        //one dim array will be size rows*columns
        int[] converted = new int[rows * columns];

        //for each row, copy its contents into the one dim
        for(int i = 0; i < rows; i++){
            System.arraycopy(toConvert[i], 0, converted, i * columns, columns);
        }

        //return the one dim array
        return converted;
    }

    //convert the sorted onedim array back into a multidim
    private static int[][] toMultiDim(int[] toConvert, int rows, int columns){
        //multidim array will be sized as follows
        int[][] converted = new int[rows][columns];

        //for each set of values, copy them into the multidim array.
        for(int i = 0; i < rows; i++){
            System.arraycopy(toConvert, (i*columns), converted[i], 0, columns);
        }

        //return the multidim array
        return converted;
    }

    //calling method, used elsewhere.
    public static int[][] sort(int[][] toSort, int rows, int columns){
        //the onedim array to be sorted, converted from the given
        int[] oneDim = toOneDim(toSort, rows, columns);
        //perform quicksort on this onedim
        quickSort(oneDim, 0, rows*columns-1);
        //convert back into a multidim, and return it.
        return toMultiDim(oneDim, rows, columns);
    }
}
