/**
 * Created by pranav on 05/07/17.
 */
public class MinCost {
    int cost[][] = {{1,2,3}, {4,8,2},{1,5,3}};
    int tc[][] = new int[4][4];
    public static void main(String[] args) {
        int m = 2, n = 2;
        MinCost mc = new MinCost();
        mc.solve(m,n);
        mc.print();
    }
    void solve(int m, int n){
        tc[0][0] = cost[0][0];
        for (int i = 1; i < 3; i++)
            tc[i][0] = tc[i-1][0] + cost[i][0];
        for (int i = 1; i < 3 ; i++)
            tc[0][i] = tc[0][i -1] + cost[0][i];
        for (int i = 1; i < 3; i++)
            for (int j = 1; j <3 ; j++)
                tc[i][j] = Math.min(Math.min(tc[i-1][j],tc[i][j-1]),tc[i-1][j-1]) + cost[i][j];
        System.out.println(tc[m][n]);
    }
    void print(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(tc[i][j] + " ");
            System.out.println();
        }
    }
}
