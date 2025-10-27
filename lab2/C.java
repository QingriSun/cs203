import java.util.Scanner;

public class C{ // 20:59
    public static void main(String[] argv){
        // read the input
        Scanner scanner = new Scanner(System.in);
        int test_num = scanner.nextInt();
        long[] output = new long[test_num];
        
        for (int i = 0; i < test_num; i++){
            int arr_len = scanner.nextInt();
            int[] arr = new int[arr_len];
            for (int j = 0; j < arr_len; j++){
                arr[j] = scanner.nextInt();
            }

            long swap_time = 0;
            output[i] = sort(arr, swap_time);
        }

        for (int i = 0; i < test_num; i++){
            System.out.println(output[i]);
        }

    }

    private static long sort(int[] arr, long swap_time){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length - (i + 1); j++){
                if (arr[j] > arr[j + 1]){
                    swap(j, j + 1, arr);
                    swap_time++;
                }
            }
        }
        return swap_time;
    }

    private static void swap(int x, int y, int[] arr){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}