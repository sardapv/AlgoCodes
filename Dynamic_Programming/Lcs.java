/**
 * Created by pranav on 04/07/17.
 */
public class Lcs {
    String string1 = "GXTXAYB";
    String string2 = "AGGTAB";
    int LCS[][] = new int[string1.length() + 1][string2.length() + 1];

    public static void main(String[] args) {
        Lcs lcs = new Lcs();
        lcs.initialize();
        lcs.lcsCalculate(lcs.string1, lcs.string2);
        lcs.print(); //just for reference
    }
    void initialize() {
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 1; j++)
                LCS[i][j] = 0;
    }
    void lcsCalculate(String s1, String s2) {
        for (int i = 1; i <= s1.length(); i++)
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1))
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                else
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
            }
        System.out.println(LCS[s1.length()][s2.length()]);
    }
    void print(){
        for (int i = 0; i <= string1.length(); i++) {
            for (int j = 0; j <= string2.length(); j++)
                System.out.print(LCS[i][j] + " ");
            System.out.println();
        }
    }
}
