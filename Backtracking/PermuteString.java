/**
 * Created by pranav on 13/07/17.
 */
public class PermuteString {
    String string = "sarda";
    public static void main(String[] args) {
        PermuteString ps = new PermuteString();
        ps.permute(ps.string, 0, ps.string.length()-1);
    }
    void permute(String here, int l, int r){
        if(l==r)
            System.out.println(here);
        else{
            for (int i = l; i <= r; i++) {
                here  = swap(here, l , i);
                permute(here, l+1, r);
                here =  swap(here, l, i);
            }
        }

    }
    String swap(String str, int i, int j){
        char stringArray[] = str.toCharArray();
        char temp = stringArray[i];
        stringArray[i] = stringArray[j];
        stringArray[j] = temp;
        return String.valueOf(stringArray);
    }
}
