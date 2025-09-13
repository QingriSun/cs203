import java.util.Scanner;

public class F{
    public static void main(String[] argv){

        Scanner scanner = new Scanner(System.in);
        int case_num = scanner.nextInt();

        // create a bucket
        int[][] bucket = new int[case_num][34];
        String type = "wbsz";
        // store the input data
        for (int i = 0; i < case_num; i++){
            String tiles = scanner.next();
            for (int j = 0; j < 14; j++){ // 14 tiles in one case
                char c1 = tiles.charAt(j * 2);
                char c2 = tiles.charAt(j * 2 + 1);

                int start = type.indexOf(c2) * 9;
                int index = start + (int) c1 - (int) '1';

                bucket[i][index]++;
            }
        }
        scanner.close();
        
        // store the result
        boolean[] result = new boolean[case_num];
        for (int i = 0; i < case_num; i++){
            result[i] = check_win(bucket[i]);
        }


        // print the result
        for (int i = 0; i < case_num; i++){
            if (result[i] == false) System.out.println("Bad luck");
            else System.out.println("Blessing of Heaven");
        }
    }

    private static int single_index(int[] bucket){
        for (int i = 0; i < 34; i++){
            if (bucket[i] == 1){
                return i;
            }
        }
        return -1; // no single number type
    }

    private static int[] copy_arr(int[] old){
        int[] _new = new int[old.length];
        for (int i = 0; i < old.length; i++){
            _new[i] = old[i];
        }
        return _new;
    }

    private static boolean check_no_single(int[] bucket){ // check if the no single array a winning condition
        int[] new_bucket = copy_arr(bucket);
        int quote_num = 0;
        for (int i = 0; i < new_bucket.length; i++){
            if (new_bucket[i] % 3 == 2){ // to ensure the 2 + 3 type can be judged correctly
                if (quote_num < 2){
                    quote_num++;
                    new_bucket[i] -= 2;
                } 
                else return false; // Too many two number types, not winning case
            }else if (new_bucket[i] != 0){
                if (new_bucket[i] % 3 != 0) return false; // The remain number of one type can not from kezi, not wining case
            }
        }
        if (quote_num != 1){
            return false; // no quote, not winning case
        }
        return true; // winning case
    }

    private static void change_arr_1(int[] bucket, int index){
        bucket[index - 2]--;
        bucket[index - 1]--;
        bucket[index]--;
    }
    private static void change_arr_2(int[] bucket, int index){
        bucket[index - 1]--;
        bucket[index]--;
        bucket[index + 1]--;
    }
    private static void change_arr_3(int[] bucket, int  index){
        bucket[index]--;
        bucket[index + 1]--;
        bucket[index + 2]--;
    }

    private static boolean check_win(int[] bucket){

        int index = single_index(bucket);
        if (index != -1){
            int length = bucket.length;
            if (index - 2 >= 0 && bucket[index - 1] != 0 && bucket[index - 2] != 0){
                int[] new_bucket = copy_arr(bucket);
                change_arr_1(new_bucket, index);
                if (single_index(new_bucket) != -1) return check_win(new_bucket);
                if (check_no_single(new_bucket) == true) return true;             
            }
            if (index - 1 >= 0 && index + 1 < length - 7 && bucket[index - 1] != 0 && bucket[index + 1] != 0){
                int[] new_bucket = copy_arr(bucket);
                change_arr_2(new_bucket, index);
                if (single_index(new_bucket) != -1) return check_win(new_bucket);
                if (check_no_single(new_bucket) == true) return true;          
            }
            if (index + 2 < length - 7 && bucket[index + 1] != 0 && bucket[index + 2] != 0){
                int[] new_bucket = copy_arr(bucket);
                change_arr_3(new_bucket, index);
                int new_index = single_index(new_bucket);
                if (new_index != -1) return check_win(new_bucket);
                if (check_no_single(new_bucket) == true) return true;               
            }
            return false; // if all the grouping method didn't return a true, then return false
        }else{
            return check_no_single(bucket); // besides the first bucket, other check win is executed only when check_no_single is not -1
        }


    }
}