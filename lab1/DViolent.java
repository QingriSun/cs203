import java.util.Scanner;

public class DViolent{
    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);
        int arr_len = scanner.nextInt();
        int sum = scanner.nextInt();
        int[] arr = new int[arr_len];
        for (int i = 0; i < arr_len; i++){
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        int tuple_num = 0;
        for (int i = 0; i < arr_len; i++){
            for (int j = i + 1; j < arr_len; j++){
                for (int k = j + 1; k < arr_len; k++){
                    if (arr[i] + arr[j] + arr[k] == sum) tuple_num++; 
                }
            }
        }

        System.out.println(tuple_num);
    }
}