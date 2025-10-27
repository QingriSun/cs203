import java.util.Scanner;

public class B{
    public static void main(String[] argv){

        Scanner scanner = new Scanner(System.in);
        int arr_len = scanner.nextInt();
        int[] arr = new int[arr_len];
        for (int i = 0; i < arr_len; i++){
            arr[i] = scanner.nextInt();
        }

        int case_num = scanner.nextInt();
        int[] input = new int[case_num];
        boolean[] output = new boolean[case_num];
        for (int i = 0; i < case_num; i++){
            input[i] = scanner.nextInt();
        }

        scanner.close();

        for (int i = 0; i < case_num; i++){
            int L = 0;
            int R = arr_len - 1;
            int mid;
            while (L < R){
                mid = (L + R + 1) / 2; // pick the left or right of the final two boxes
                if (arr[mid] < input[i]){
                    L = mid;
                }
                else if(arr[mid] > input[i]){
                    R = mid - 1; // mid can not be the possible index
                }
                else{
                    L = mid;
                }
            }
            if (arr[L] == input[i]){
                output[i] = true;
            }else{
                output[i] = false;
            }        
        }

        for (int i = 0; i < case_num; i++){
            if (output[i] == true){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }

    }


}