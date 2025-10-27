import java.util.Arrays;
import java.util.Scanner;

public class E{
    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);

        int test_num = 0;
        int[] output = {};

        while (scanner.hasNext()){
            int race_len = scanner.nextInt();
            int place_num = scanner.nextInt();
            int mb_num = scanner.nextInt(); // member number
            int[] place = new int[place_num + 1]; // the last element is the whole length
            for (int i = 0; i < place_num; i++){
                place[i] = scanner.nextInt();
            }
            place[place_num] = race_len;

            Arrays.sort(place);

            int L = 0;
            int R = (int) 1e9;
            int mid;
            while (L < R){
                mid = L + (R - L) / 2;
                if (canReach(mid, mb_num, place, race_len) == true){
                    R = mid;
                }else{
                    L = mid + 1;
                }
            }

            test_num++;
            int output1[] = new int[test_num];
            for (int i = 0; i < output.length; i++){
                output1[i] = output[i];
            }
            output1[test_num - 1] = L; // add the result of this while loop
            output = output1;

        }

        scanner.close();

        for (int i = 0; i < output.length; i++){
            System.out.println(output[i]);
        }

    }

    private static boolean canReach(int min_dis, int mb_num, int[] place, int race_len){
        int total_dis = 0;
        int added_dis = 0;
        for (int i = 0; i < mb_num; i++){
            // search for the distance smaller or equal than added dis to be the new total dis
            added_dis += min_dis;
            if (added_dis >= race_len){
                return true;
            }
            int L = 0;
            int R = place.length - 1;
            int mid;
            while (L < R){
                mid = (L + R + 1) / 2;
                if (place[mid] < added_dis){
                    L = mid;
                }else if (place[mid] > added_dis){
                    R = mid - 1;
                }else{
                    L = mid;
                }
            }
            total_dis = place[L];
            added_dis = total_dis;
        }

        return false;
    }
}
