/**
 * Created by pranav on 05/07/17.
 */
public class CoinChange {
    int Sum[] = {1,2,3};
    int amount = 10;
    int cost[][] = new int[Sum.length+1][amount+1];

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        cc.initialize();
        cc.calculate();
        cc.print();
    }
    void initialize(){
        for (int i = 0; i <= Sum.length; i++)
            cost[i][0] = 1;
    }
    void calculate(){
        for (int i = 1; i <= Sum.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if(j>=Sum[i-1])
                    cost[i][j] = cost[i-1][j] + cost[i][j-Sum[i-1]];
                else
                    cost[i][j] = cost[i-1][j];
            }
        }
        System.out.println(cost[Sum.length][amount]);
    }
    void print(){
        for (int i = 0; i <= Sum.length; i++) {
            for (int j = 0; j <= amount; j++)
                System.out.printf(cost[i][j]+"\t");
            System.out.println();
        }
    }
}
