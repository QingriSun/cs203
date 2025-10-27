import java.util.Scanner;

public class D{
    public static void main(String[] argv){
        // read and store input in a two dimension array
        // the second dimension is of different length

        Scanner scanner = new Scanner(System.in);
        int len1 = scanner.nextInt();
        int[][] input = new int[len1][];

        for (int i = 0; i < len1; i++){
            int len2 = scanner.nextInt();
            input[i] = new int[len2];
            for (int j = 0; j < len2; j++){
                input[i][j] = scanner.nextInt();
            }
        }

        scanner.close();

        int[] output = new int[len1];

        for (int i = 0; i < len1; i++){
            int max_num = input[i][0];
            int max_difference = input[i][0] - input[i][1];

            // find the largest integer of the first 1 th numbers
            for (int j = 0; j < input[i].length - 1; j++){
                if (input[i][j] > max_num){
                    max_num = input[i][j];
                }
                if (max_num - input[i][j + 1] > max_difference){
                    max_difference = max_num - input[i][j + 1];
                }
            }

            output[i] = max_difference;
        }

        // print the result
        for (int i = 0; i < len1; i++){
            System.out.println(output[i]);
        }
    }
}

// finish in 24 min 30 sec