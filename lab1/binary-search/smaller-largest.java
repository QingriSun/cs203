import java.util.Scanner;

public class SmallerLargest{
    public static void main(String[] argv){
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++){
            arr[i] = i;
        }
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        int L = 0;
        int R = arr.length - 1;
        int mid;

        while (L < R){
            mid = (L + R + 1) / 2;
            if (input < mid){
                R = mid - 1;
            }
            else if (input > mid){
                L = mid;
            }
            else{
                L = mid;
            }
        }g
        System.out.println(L);
    }
}