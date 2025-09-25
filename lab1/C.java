import java.util.Scanner;

public class C{
    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);
        int arr_len = scanner.nextInt();
        int query_num = scanner.nextInt();
        // store the non-decreasing array
        int[] arr = new int[arr_len];
        for (int i = 0; i < arr_len; i++){
            arr[i] = scanner.nextInt();
        }

        // process the query
        int x;
        int y;
        int larger_smallest;
        int smaller_largest;
        int[] result = new int[query_num];
        for (int i = 0; i < query_num; i++){
            x = scanner.nextInt();
            if (x >= arr[arr_len - 1]){
                scanner.nextInt(); // consume the remaining input
                 continue;
            } // it is impossible to have number greater than x in this array
            larger_smallest = larger_smallest(arr, x);
            y = scanner.nextInt();
            if (y <= arr[0]) continue;
            smaller_largest = smaller_largest(arr, y);
            result[i] = smaller_largest - larger_smallest + 1;
        }
        scanner.close();

        for (int i = 0; i < query_num; i++){
            if (result[i] > 0){
                System.out.printf("YES %d\n", result[i]);
            }else{
                System.out.println("NO");
            }
        }
    }
    
    // find the index of the smallest number larger than x
    private static int larger_smallest(int[] arr, int x){
        int L = 0; 
        int R = arr.length - 1;
        while (L != R){
            int mid = (L + R) / 2;

            if (x < arr[mid]){
                R = mid;
            }
            else if (x > arr[mid]){
                L = mid + 1;
            }
            else{
                L = mid + 1;
            }
        }
        return L;
    }

    // find the index of the largest number smaller than y
    private static int smaller_largest(int[] arr, int y){
        int L = 0;
        int R = arr.length - 1; 

        while (L != R){
            int mid = (L + R + 1) / 2;
            if (y < arr[mid]){
                R = mid - 1;
            }
            else if (y > arr[mid]){
                L = mid;
            }
            else{
                R = mid - 1;
            }
        }
        return L;
    }
}