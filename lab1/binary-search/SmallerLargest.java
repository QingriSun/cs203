import java.util.Scanner;

public class SmallerLargest{
    public static void main(String[] argv){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the length of the array: ");
        int len = scanner.nextInt();
        int[] arr = new int[len];

        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < len; i++){
            arr[i] = scanner.nextInt();
        }
        System.out.println("Enter the query number: ");
        int input = scanner.nextInt();

        scanner.close();


        int L = 0;
        int R = arr.length - 1;
        int mid;

        while (L < R){
            mid = (L + R + 1) / 2;
            if (input < arr[mid]){
                R = mid - 1;
            }
            else if (input > arr[mid]){
                L = mid;
            }
            else{
                R = mid - 1;
            }
        }
        System.out.printf("The index of the result is %d, the value of the result is %d", L, arr[L]);
    }
}