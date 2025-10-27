import java.util.Scanner;

public class FSolution{
    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);
        int case_num = scanner.nextInt();

        // create a bucket
        int[][] bucket = new int[case_num][34 + 3]; // add 3 to separate w, b, s, z
        String type = "wbsz";
        // store the input data
        for (int i = 0; i < case_num; i++){
            String tiles = scanner.next();
            for (int j = 0; j < 14; j++){ // 14 tiles in one case
                char c1 = tiles.charAt(j * 2);
                char c2 = tiles.charAt(j * 2 + 1);

                int start = type.indexOf(c2) * 10;
                int index = start + (int) c1 - (int) '1';

                bucket[i][index]++;
            }
        }
        scanner.close();

        // loop through all the situation of deleting a quote
        boolean[] win = new boolean[case_num];
        for (int i = 0; i < case_num; i++){
            for (int j = 0; j < bucket[i].length; j++){
                if (bucket[i][j] >= 2){
                    bucket[i][j] -= 2;
                    win[i] = check_case(bucket[i]);
                    if (win[i] == true){
                        break;
                    }
                    bucket[i][j] += 2;
                }
            }
        }
        for (int i = 0; i < case_num; i++){
            if (win[i] == true) System.out.println("Blessing of Heaven");
            else System.out.println("Bad luck");
        }

    }

    private static int[] copy_arr(int[] old){
        int[] _new = new int[old.length];
        for (int i = 0; i < old.length; i++){
            _new[i] = old[i];
        }
        return _new;
    }

    // To the bucket at the end, if they have three kezi, it is of same effect that they form kezi or shunzi
    // delete all possible kezi and shunzi of the end, and then push the end forward
    private static boolean check_case(int[] bucket){
        int[] new_bucket = copy_arr(bucket);
        for (int i = 0; i < new_bucket.length - 2; i++){
            int tile_num = new_bucket[i];
            if (tile_num >= 3){
                new_bucket[i] -= 3 * (tile_num / 3);
                tile_num -= 3 * (tile_num / 3);
            }
            if (i < new_bucket.length - 7 && tile_num >= 1){
                if (new_bucket[i + 1] >= tile_num && new_bucket[i + 2] >= tile_num){
                    new_bucket[i] = 0;
                    new_bucket[i + 1] -= tile_num;
                    new_bucket[i + 2] -= tile_num;
                }else{
                    return false;
                }
            }
            if (new_bucket[i] > 0){
                return false;
            }
        }
        int len = new_bucket.length;
        if (new_bucket[len - 2] % 3 != 0 || new_bucket[len - 1] % 3 != 0){
            return false;
        }
        return true;
    }
}

