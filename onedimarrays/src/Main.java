public class Main {

    public static void main(String[] args) {
        //generate the one dimensional array and accompanying object with methods
        OneDimGen oneDim = new OneDimGen(10,0,100);
        oneDim.showArr();
        oneDim.showMean();
        oneDim.sortArr(true);
        oneDim.showMin();
        oneDim.showMax();
        oneDim.showArr();
        oneDim.sortArr(false);
        oneDim.showArr();
    }
}
