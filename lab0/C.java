import java.util.Scanner;

public class C{

    private static int indexOf(String a){
        String[] tiles = {
            "W9", "W7", "W6", "W5", "W4", "W3", "W2", "W1",
            "T9", "T7", "T6", "T5", "T4", "T3", "T2", "T1",
            "Y9", "Y7", "Y6", "Y5", "Y4", "Y3", "Y2", "Y1",
            "E", "S", "E", "N", "B", "F", "Z"
        };
        for (int i = 0; i < tiles.length; i++){
            if (a.equals(tiles[i])){
                return i;
            }
        }
        return -1;
    }


     public static void main(String[] argv){
        // sorting Mahjong tiles, // 20:23

        // read the number of series
        Scanner scanner = new Scanner(System.in);
        int num_series = scanner.nextInt();

        // replace the input with numbers
        int[][] input = new int[num_series][13];
        for (int i = 0; i < num_series; i++){
            for (int j = 0; j < 13; j++){
                input[i][j] = indexOf(scanner.next());
            }
        }
        scanner.close();


        // convert and store each input
        
    }
}