/**
 * Created by pranav on 04/07/17.
 */
public class Lis {
    int arr[] = {10, 22, 9, 33, 21, 50, 41, 60, 80};
    int LIS[] = new int[arr.length];
    public static void main(String[] args) {
        Lis lis = new Lis();
        lis.initialize();
        lis.lisCalculate(lis.arr, lis.LIS);
    }
    void initialize(){
        for (int i = 0; i < arr.length; i++)
            LIS[i] = 1;
    }
    void lisCalculate(int arr[], int LIS[]){
        int max = 0;
        int indexLefttoPrint = 0;
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j <= i; j++)
                if((arr[i] > arr[j]) && (LIS[i] < LIS[j]+1)){
                    LIS[i] = 1 + LIS[j];
                    if(LIS[i] > max){
                        max = LIS[i];
                        System.out.print(arr[j]+" ");
                        indexLefttoPrint = i;
                    }
                }
        System.out.print(arr[indexLefttoPrint]+"");
        System.out.printf("\n"+max);
    }
}
