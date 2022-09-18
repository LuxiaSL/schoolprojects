public class Main {
    public static void main(String[] args) {
        MultiDimGen multiDim = new MultiDimGen(4,3,0,100);
        System.out.println("Unsorted array is as follows:");
        multiDim.showMatrixForm();
        System.out.println("-----------------------------");
        multiDim.showSum();
        multiDim.showMin();
        multiDim.showMax();
        System.out.println("-----------------------------");
        multiDim.showOdds();
        System.out.println("-----------------------------");
        System.out.println("Sorted array is as follows:");
        multiDim.showMatrixForm();
        System.out.println("-----------------------------");
        multiDim.sortArr(false);
        System.out.println("Reversed array is as follows:");
        multiDim.showMatrixForm();
    }
}
