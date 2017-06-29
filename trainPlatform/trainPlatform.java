import java.util.Arrays;

/**
 * Created by pranav on 29/06/17.
 */
public class trainPlatform {
    public static void main(String args[]){
        int arr[] = {900, 940, 950, 1100, 1500, 1800};
        int dep[] = {910, 1200, 1120, 1130, 1900, 2000};
        Arrays.sort(arr);
        Arrays.sort(dep);
        System.out.println("Min platforms required = "+findOverlaps(arr,dep));
     }
     public static int findOverlaps(int arrival[], int departure[]){
        int n = arrival.length;

        int platformsRequired = 0;
        int maxCount = 0;
        int i = 0, j = 0;

        while (i < n && j < n){
            if (arrival[i] < departure[j]){
                platformsRequired++;
                i++;
                if (platformsRequired > maxCount)
                    maxCount = platformsRequired;
            }
            else{
                j++;
                platformsRequired--;
            }
        }
        return  maxCount;
     }
}
