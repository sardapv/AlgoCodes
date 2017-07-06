public class MatrixChainMultiplication{
    int arr[] = {4,2,3,5,3};
    int cost[][] = new int [arr.length][arr.length];
    public static void main(String[] args) {
        MatrixChainMultiplication mcm  = new MatrixChainMultiplication();
        mcm.compute();
        mcm.print();
    }
    void compute(){
        for (int l = 2; l < arr.length ; l++)
            for (int i = 0; i < arr.length -l ; i++) {
                int j = i + l;
                int temp = 0;
                cost[i][j] = 100000;
                for (int k = i+1; k < j ; k++) {
                    temp = cost[i][k] + cost[k][j] + arr[i]*arr[k]*arr[j];
                    if(temp < cost[i][j])
                        cost[i][j] = temp;
                }
            }
        System.out.println(cost[0][arr.length-1]);
    }
    void print(){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++)
                System.out.printf(cost[i][j]+"\t");
            System.out.println();
        }
    }
}