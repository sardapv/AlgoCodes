/**
 * Created by pranav on 04/07/17.
 */
public class EditDistance {
    String string1 = "CART";
    String string2 = "MARCH";
    int distance[][] = new int[string1.length() + 1][string2.length() + 1];

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        ed.initialize();
        ed.edCalculate(ed.string1, ed.string2);
        ed.print(); //just for reference
    }
    void initialize() {
        int count = 0;
        int i = 0;
            for (int j = 0; j <= string2.length(); j++)
                distance[i][j] = count++;
        count = 0;
        int j = 0;
            for ( i = 0; i <= string1.length(); i++)
                distance[i][j] = count++;
    }
    void edCalculate(String s1, String s2) {
        for (int i = 1; i <= s1.length(); i++)
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) != s2.charAt(j-1)){
                    distance[i][j] = Math.min(distance[i - 1][j], distance[i][j - 1]);
                    distance[i][j] = Math.min(distance[i - 1][j - 1],distance[i][j])+1;
                }
                else
                    distance[i][j] = distance[i - 1][j - 1];
            }
        System.out.println(distance[s1.length()][s2.length()]);
    }
    void print(){
        for (int i = 0; i <= string1.length(); i++) {
            for (int j = 0; j <= string2.length(); j++)
                System.out.print(distance[i][j] + " ");
            System.out.println();
        }
    }
}
