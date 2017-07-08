/**
 * Created by pranav on 07/07/17.
 */
public class EggDrop {
    int n = 2;
    int k = 100;
    int cost[][] = new int[n+1][k+1];
    public static void main(String[] args) {
        EggDrop ed = new EggDrop();
        ed.initialize();
        ed.compute();
        //ed.print();
    }
    void initialize(){
        for (int i = 1; i < k+1 ; i++)
            cost[1][i] = i;
    }
    void print(){
        for (int i = 1; i < n+1 ; i++) {
            for (int j = 1; j < k+1 ; j++)
                System.out.printf(cost[i][j]+"\t");
            System.out.println();
        }
    }
    void compute(){
        for (int i = 2; i <=n ; i++)
            for (int j = 1; j <=k ; j++) {
                cost[i][j] = Integer.MAX_VALUE;
                int temp = 0;
                for (int k = 1; k <= j ; k++) {
                        temp = 1+Math.max(cost[i][j-k],cost[i-1][k-1]);
                    if(temp < cost[i][j])
                        cost[i][j] = temp;
                }
            }
        System.out.println(cost[n][k]);
    }
}
