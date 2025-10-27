import java.util.Scanner;

public class D{
    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);
        int arr_len = scanner.nextInt();
        int sum = scanner.nextInt();
        int[] arr = new int[arr_len];
        for (int i = 0; i < arr_len; i++){
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        int remain;
        long tuple_num = 0;
        int smaller_equal;
        int larger_equal;
        for (int i = 0; i < arr_len; i++){
            for (int j = i + 1; j < arr_len; j++){
                remain = sum - arr[i] - arr[j];
                smaller_equal = smaller_equal(arr, remain);
                if (arr[smaller_equal] == remain){
                    larger_equal = larger_equal(arr, remain); // if there is required element, than arr[larger_equal] = arr[smaller_equal]
                    if (smaller_equal > j){ // the precondition that there exists such tuple
                        if (larger_equal > j) tuple_num += smaller_equal - larger_equal + 1;
                        else tuple_num += smaller_equal - j;
                    }
                }

            }
        }
        System.out.println(tuple_num);
    }


    // find the value of the element that is smaller or equal to input
    private static int smaller_equal(int[] arr, int input){
        int L = 0;
        int R = arr.length - 1;
        int mid;
        while (L != R){
            mid = (L + R + 1) / 2;
            if (input > arr[mid]) L = mid;
            else if (input < arr[mid]) R = mid - 1;
            else L = mid;
        }
        return L;
    }

    private static int larger_equal(int[] arr, int input){
        int L = 0;
        int R = arr.length - 1;
        int mid;
        while (L != R) {
            mid = (L + R) / 2;
            if (input > arr[mid]){
                L = mid + 1;
            }
            else if (input < arr[mid]){
                R = mid;
            }
            else{
                R = mid;
            }
        }
        return L;

    }
}


