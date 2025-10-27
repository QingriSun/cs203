import java.util.Scanner;

public class C1{
    public static void main(String[] argv){

        // read the input
        Scanner scanner = new Scanner(System.in);
        int test_num = scanner.nextInt();
        long[] output = new long[test_num];
        
        for (int i = 0; i < test_num; i++){
            long[] swap_num = new long[] {0};

            int arr_len = scanner.nextInt();
            int[] arr = new int[arr_len];
            for (int j = 0; j < arr_len; j++){
                arr[j] = scanner.nextInt();
            }

            sort(arr, 0, arr_len - 1, swap_num);

            output[i] = swap_num[0];

        }

        scanner.close();

        for (int i = 0; i < test_num; i++){
            System.out.println(output[i]);
        }

    }

    private static int[] sort(int[] arr, int L, int R, long[] swap_num){
        if (R - L == 0){
            return new int[] {arr[L]};
        }else if (R - L == 1){
            if (arr[L] > arr[R]){
                swap_num[0]++;
                return new int[] {arr[R], arr[L]};
            } 
            else return new int[] {arr[L], arr[R]}; 
        }else{
            int mid  = R - (R - L) / 2;
            int[] L_arr = sort(arr, L, mid - 1, swap_num);
            int[] R_arr = sort(arr, mid, R, swap_num);

            return merge(L_arr, R_arr, swap_num);
        }
    }

    private static int[] merge(int[] L, int[] R, long[] swap_num){
        int[] new_arr = new int[L.length + R.length];
        int L_pnt = 0;
        int R_pnt = 0;
        int sorted = 0;
        while (L_pnt < L.length && R_pnt < R.length){
            if (L[L_pnt] <= R[R_pnt]) new_arr[sorted++] = L[L_pnt++];
            else{
                swap_num[0] += (L.length - L_pnt);
                new_arr[sorted++] = R[R_pnt++];
            }
        }

        for (; L_pnt < L.length;){
            new_arr[sorted++] = L[L_pnt++];
        }
        for (; R_pnt < R.length;){
            new_arr[sorted++] = R[R_pnt++];
        }

        return new_arr;
    }
}