import java.util.Scanner;

public class C1{ // 07:48 - 08:57
    final private static String order1 = "WTY";
    final private static String order2 = "ESWNBFZ";

    public static void main(String[] argv){
        // store the input
        Scanner scanner = new Scanner(System.in);
        int num_series = scanner.nextInt();

        int[][] input = new int[num_series][13];

        // convert the input into numbers
        for (int i = 0; i < num_series; i++){
            for (int j = 0; j < 13; j++){
                String in = scanner.next();
                if (in.length() == 2){
                    input[i][j] += order1.indexOf(in.charAt(0)) * 10;
                    input[i][j] += (int) in.charAt(1) - (int) '0';
                }
                else{
                    input[i][j] = 30; // make it larger then all len2 input
                    input[i][j] += order2.indexOf(in.charAt(0));
                }
            }
        }
        scanner.close();

        // sort each series
        for (int i = 0; i < num_series; i++){
            for (int j = 0; j < 12; j++){
                int smallest = j + 1;
                for (int k = j + 1; k < 13; k++){
                    if (input[i][k] < input[i][smallest]){
                        smallest = k;
                    }
                }
                if (input[i][smallest] < input[i][j]){ // check if there is necessity to swap
                    int temp = input[i][j];
                    input[i][j] = input[i][smallest];
                    input[i][smallest] = temp;
                }
            }
        }

        // print the output
        for (int i = 0; i < num_series; i++){
            for (int j = 0; j < 13; j++){
                if (input[i][j] >= 30){
                    System.out.printf("%c ", order2.charAt(input[i][j] % 30));
                }
                else{
                    System.out.printf("%c%d ", order1.charAt(input[i][j] / 10), input[i][j] % 10);
                }
            }
            System.out.printf("\n");
        }

    }
}