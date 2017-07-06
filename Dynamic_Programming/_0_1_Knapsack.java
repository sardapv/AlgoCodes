/**
 * Created by pranav on 06/07/17.
 */
public class _0_1_Knapsack {
    int lengthinput = 7;
    int val[] = {1,4,5,7};
    int weight[] = {1,3,4,5};
    int cost[][] = new int[weight.length][lengthinput+1];
    public static void main(String[] args) {
        _0_1_Knapsack ks = new _0_1_Knapsack();
        ks.initialize();
        ks.compute();
        ks.print();
    }
    void initialize(){
        for (int i = 1; i <= lengthinput; i++)
            cost[0][i] = val[0];
    }
    void compute(){
        for (int i = 1; i < val.length ; i++)
            for (int j = 0; j <= lengthinput; j++) {
                if((j-weight[i])>=0)
                    cost[i][j] = Math.max(cost[i-1][j-weight[i]]+ val[i], cost[i-1][j]);
                else
                    cost[i][j] = cost[i-1][j];
            }
        System.out.println(cost[val.length-1][lengthinput]);
    }
    void print(){
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j <= lengthinput; j++)
                System.out.printf(cost[i][j]+"\t");
            System.out.println();
        }
    }
}
