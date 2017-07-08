/**
 * Created by pranav on 07/07/17.
 */
public class LonestPalindromeSubsequence {
    String string = "GEEKS FOR GEEKS";
    int length = string.length();
    int cost[][] = new int[length][length];
    public static void main(String[] args) {
        LonestPalindromeSubsequence lps = new LonestPalindromeSubsequence();
        lps.initialize();
        lps.compute();
        lps.print();
    }
    void initialize(){
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                if(i==j)
                    cost[i][j] = 1;
    }
    void compute(){
            for (int l = 2; l <= length ; l++) {
                for (int i = 0; i <= length - l ; i++) {
                    int j = i+l-1;
                    cos
//                    if(l==2 && string.charAt(i) == string.charAt(j))
//                        cost[i][j] = 2;
//                    else if(string.charAt(i) == string.charAt(j))
//                        cost[i][j] = 2 + cost[i+1][j-1];
//                    else
//                        cost[i][j] = Math.max(cost[i+1][j], cost[i][j-1]);
                }
            }
        System.out.println(cost[0][length-1]);
    }
    void print(){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++)
                System.out.printf(cost[i][j] + "\t");
            System.out.println();
        }
    }
}
