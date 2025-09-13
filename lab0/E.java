import java.util.Scanner;

public class E{ // 25min + 21:00 - 
    public static void main(String[] argv){

        Scanner scanner = new Scanner(System.in);
        int case_num = scanner.nextInt();
        int[][] cube = new int[case_num][3];

        // store the information of cubes
        for (int i = 0; i < case_num; i++){
            for (int j = 0; j < 3; j++){
                cube[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        // create a 2 dimension array to store char
        char[][][] output = new char[case_num][][];

        for (int i = 0; i < case_num; i++){

            // the size of output size of each case is associate with length, width, height
            int graph_wid = (cube[i][0] + cube[i][1]) * 2 + 1;
            int graph_hei = (cube[i][1] + cube[i][2]) * 2 + 1;
            output[i] = new char[graph_hei][graph_wid];

            // fill the cube with initial values '.'
            for (int j = 0; j < graph_hei; j++){
                for (int k = 0; k < graph_wid; k++){
                    output[i][j][k] = '.';
                }
            }

            // find the index of the critical point
            int index_i = cube[i][1] * 2;
            int index_j = cube[i][0] * 2;

            // fill the top surface
            for (int j = 0; j < cube[i][1]; j++){ // loop for #width times
                for (int k = 0; k < cube[i][0]; k++){ // loop for #length times
                    // fill with "./"
                    output[i][index_i - 1 - j * 2][j * 2 + 1 + k * 2] = '/';

                    // fill with "+-"
                    output[i][index_i - 2 - j * 2][j * 2 + 2 + k * 2] = '+';
                    output[i][index_i - 2 - j * 2][j * 2 + 3 + k * 2] = '-';
                }

            }

            // fill the front surface
            for (int j = 0; j < cube[i][2]; j++){ // loop for #height time
                for (int k = 0; k < cube[i][0]; k++){ // loop for #length time
                    // fill with "+-"
                    output[i][index_i + j * 2][2 * k] = '+';
                    output[i][index_i + j * 2][1 + 2 * k] = '-';

                    // fill with "|."
                    output[i][index_i + 1 + j * 2][2 * k] = '|';
                }
            }

            // fill the left side
            int j_1 = 0;
            for (; j_1 < cube[i][1]; j_1++){
                int k = 0;
                for (; k < cube[i][2]; k++){
                    // fill with + |
                    output[i][index_i - j_1 * 2 + k * 2][index_j + 2 * j_1] = '+';
                    output[i][index_i - j_1 * 2 + 1 + k * 2][index_j + 2 * j_1] = '|';

                    // fill with / .
                    output[i][index_i - 1 - j_1 * 2 + k * 2][index_j + 1 + 2 * j_1] = '/';
                }
                // fill the right bottom edge
                k--; // k = 0, k++ in the final loop. To keep the following k equal to the last used k, do k--
                output[i][index_i - j_1 * 2 + 1 + k * 2 + 1][index_j + 2 * j_1] = '+';
                output[i][index_i - 1 - j_1 * 2 + k * 2 + 2][index_j + 1 + 2 * j_1] = '/';
            }

            // fill the bottom edge
            for (int j = 0; j < cube[i][0]; j++){
                output[i][graph_hei - 1][2 * j] = '+';
                output[i][graph_hei - 1][1 + 2 * j] = '-';
            }
            // fill the right edge
            for (int j = 0; j < cube[i][2]; j++){
                output[i][2 * j][graph_wid - 1] = '+';
                output[i][1 + 2 * j][graph_wid - 1] = '|';
            }

            // the last point
            output[i][cube[i][2] * 2][graph_wid - 1] = '+';
        }

        // that print them
        for (int i = 0; i < case_num; i++){
            for (int j = 0; j < output[i].length; j++){
                for (int k = 0; k < output[i][j].length; k++){
                    System.out.printf("%c", output[i][j][k]);
                }
                System.out.printf("\n");
            }
        }
    }
}