/**
 * Created by pranav on 28/06/17.
 */

public class JobSequence {
    public static void main(String args[]){
        Job jobarray[] = {
          new Job(1,2,100), new Job(2,1,19),new Job(3,2,27),new Job(4,1,25),new Job(5,3,15)
        };
        Job sortedarray[] = mysort(jobarray);
        jobScheduling(sortedarray);
    }
    public static Job[] mysort(Job jobarr[]){
        for (int i = 0; i < jobarr.length; i++){
            for(int j = 0; j < jobarr.length - i - 1; j++){
                if(jobarr[j].profit < jobarr[j+1].profit){
                    Job temp = jobarr[j+1];
                    jobarr[j+1] = jobarr[j];
                    jobarr[j] = temp;
                }
            }
        }
        return jobarr;
    }
    public static void jobScheduling(Job jobarr[]){
        int n = jobarr.length;
        int result[]  = new int[n];
        boolean slot[] = new boolean[n];

        for (int i = 0; i < n ; i++) {
            slot[i] = false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = (Math.min(n, jobarr[i].deadline))-1; j >= 0 ; j--) {
                if (slot[j] == false){
                    result[j] = i;
                    slot[j] = true;
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if(slot[i])
                System.out.println(jobarr[result[i]].id);

        }
    }

}
