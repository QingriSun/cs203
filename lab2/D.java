    import java.util.Scanner;

public class D {
    public static void main(String[] argv){
        // read the input
        Scanner scanner = new Scanner(System.in);
        int arr_len = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[arr_len];
        for (int i = 0; i < arr_len; i++){
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        int L = 0;
        int R = arr_len - 1;
        arr = sort(L, R, arr);

        System.out.println(arr[k - 1]);
    }

    private static int[] sort(int L, int R, int[] arr){
        int[] new_arr;

        if (R - L >= 2){
            int mid = (L + R + 1) / 2;
            int[] arr_L = sort(L, mid - 1, arr);
            int[] arr_R = sort(mid, R, arr);
            int sorted = 0;
            new_arr = new int[R - L + 1];
            int L_pnt = 0;
            int R_pnt = 0;
            while (sorted < R - L + 1 && L_pnt < arr_L.length && R_pnt < arr_R.length){
                if (arr_L[L_pnt] > arr_R[R_pnt]){
                    new_arr[sorted++] = arr_R[R_pnt++];
                }else{
                    new_arr[sorted++] = arr_L[L_pnt++];
                }
            }
            for (int i = R_pnt; i < arr_R.length; i++){
                new_arr[sorted++] = arr_R[R_pnt++];
            }
            for (int i = L_pnt; i < arr_L.length; i++){
                new_arr[sorted++] = arr_L[L_pnt++];
            }

            return new_arr;
        }
        else if (R - L == 1){ // length is 2
            if (arr[L] > arr[R]){
                return new int[]{arr[R], arr[L]};
            }else{
                return new int[]{arr[L], arr[R]};
            }
        }else{ // length is 1
                return new int[]{arr[R]};
        }
    }
}

