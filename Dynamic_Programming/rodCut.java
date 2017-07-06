/**
 * Created by pranav on 06/07/17.
 */
public class rodCut {
    int lengthinput = 5;
    int price[] = {2,5,7,8};
    int cost[][] = new int[price.length][lengthinput+1];
    public static void main(String[] args) {
        rodCut rc = new rodCut();
        rc.initialize();
        rc.compute();
        rc.print();
    }
    void initialize(){
        for (int i = 0; i <= lengthinput; i++)
            cost[0][i] = i * price[0];
    }
    void compute(){
        for (int i = 1; i < price.length ; i++)
            for (int j = 1; j <= lengthinput; j++) {
                if((j-i-1)>=0)
                    cost[i][j] = Math.max(cost[i][j-i-1]+ price[i], cost[i-1][j]);
                else
                    cost[i][j] = cost[i-1][j];
            }
        System.out.println(cost[price.length-1][lengthinput]);
    }
    void print(){
        for (int i = 0; i < price.length; i++) {
            for (int j = 0; j <= lengthinput; j++)
                System.out.printf(cost[i][j]+"\t");
            System.out.println();
        }
    }
}
