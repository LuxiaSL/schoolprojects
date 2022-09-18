public class Complexity{
    public static double analyseSort(int[] toSort, int selection){
        switch (selection) {
            case 1 -> {
                //insert sort
                long start = System.nanoTime();
                insertSort(toSort);
                long end = System.nanoTime();
                return (double)((end - start)) / 1000000;
            }
            case 2 -> {
                //merge sort
                long start = System.nanoTime();
                mergeSort(toSort, 0, toSort.length-1);
                long end = System.nanoTime();
                return (double)((end - start)) / 1000000;
            }
            case 3 -> {
                //quick sort
                long start = System.nanoTime();
                quickSort(toSort, 0, toSort.length-1);
                long end = System.nanoTime();
                return (double)((end - start)) / 1000000;
            }
            default -> { return 0; /*shouldnt occur unless I typo*/ }
        }
    }

    //basically, iterate straight through the list, checking for each number to be in the right spot.
    //grab num >> compare to all others until it reaches a spot where it is greater than left, less than rightt
    private static void insertSort(int[] toSort){
        int size = toSort.length;
        for(int i = 1; i < size; i++){
            //grab value
            int toCheck = toSort[i];
            //setup iterator
            int j = i-1;
            //while there are more to check AND the new iterated one is still more than the one to check
            while(j>=0 && toSort[j] > toCheck){
                //shift the array
                toSort[j + 1] = toSort[j];
                j--;
            }
            toSort[j + 1] = toCheck;
        }
    }

    //mergesort algorithm
    //firstly, split given array into chunks
    private static void mergeSort(int[] toSort, int left, int right){
        if(right<=left) return;

        int middle = (left + right) / 2;
        mergeSort(toSort, left, middle);
        mergeSort(toSort, middle+1, right);
        merge(toSort, left, middle, right);
    }

    private static void merge(int[] toMerge, int left, int middle, int right){
        //temp subarrays
        int[] leftArr = new int[middle - left + 1];
        int[] rightArr = new int[right - middle];

        //copy into the temps
        System.arraycopy(toMerge, left, leftArr, 0, leftArr.length);
        System.arraycopy(toMerge, middle+1, rightArr, 0, rightArr.length);

        /*MANUAL ARRAYCOPY
        for(int i = 0; i < rightArr.length; i++){ leftArr[i] = toMerge[left + i]; }
        for(int i = 0; i < rightArr.length; i++){ rightArr[i] = toMerge[middle + i + 1]; }
         */

        int leftIndex = 0;
        int rightIndex = 0;

        //now, merge them
        for(int i = left; i < right + 1; i++){
            //if there are uncopied elements in both, copy the minimum of them.
            if(leftIndex < leftArr.length && rightIndex < rightArr.length){
                if(leftArr[leftIndex] <  rightArr[rightIndex]){
                    toMerge[i] = leftArr[leftIndex];
                    leftIndex++;
                }else{
                    toMerge[i] = rightArr[rightIndex];
                    rightIndex++;
                }
            }else if(leftIndex < leftArr.length){
                //means right is empty, copy the rest of the left.
                toMerge[i] = leftArr[leftIndex];
                leftIndex++;
            }else if(rightIndex < rightArr.length){
                //means left was empty, copy rest of the right
                toMerge[i] = rightArr[rightIndex];
                rightIndex++;
            }
        }
    }

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
}
