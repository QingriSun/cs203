import java.util.Scanner;

public class F1{ // 18:30 start time - 19:06 end building - 19:27 end debugging locally, and submit to oj and debug - 19:57 AC
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

        int[] quote_num = new int[case_num]; // the initial quote_num is 0

        for (int i = 0; i < case_num; i++){
            quote_num[i] = check_case(quote_num[i], bucket[i]);
        }

        for (int i = 0; i < case_num; i++){
            if (quote_num[i] == 1) System.out.println("Blessing of Heaven");
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

    private static int check_case(int quote_num, int[] bucket){ // return quote_num
        
        // find the first bucket that is not empty
        int first_no_empty = -1;
        for (int i = 0; i < bucket.length; i++){
            if (bucket[i] != 0){
                first_no_empty = i;
                break;
            }
        }
        if (first_no_empty == -1) return 1; // if not find(it is an empty array), return the right quote_num 1
        else{
            // if it can form a column
            if (bucket[first_no_empty] >= 3){ // it can form a column, return the quote_num, and delete this row from bucket and check the next first not empty bucket again
                int[] new_array = copy_arr(bucket);
                new_array[first_no_empty] -= 3;
                int new_quote_num = check_case(quote_num, new_array); // after the column is deleted, is the new_array a possible winning case
                if (new_quote_num == 1) return 1; // if that case is not a winning case, give forming row or quote a try
            } // it cannot form a column, skip the column case and check if it can form a row or a quote
            if (first_no_empty + 2 < bucket.length - 7 && bucket[first_no_empty + 1] > 0 && bucket[first_no_empty + 2] > 0){ // check if it can form a row
                int[] new_array = copy_arr(bucket);
                new_array[first_no_empty]--;
                new_array[first_no_empty + 1]--;
                new_array[first_no_empty + 2]--;
                int new_quote_num = check_case(quote_num, new_array); // after the column is deleted, is the new_array a possible winning case
                if (new_quote_num == 1) return 1; // if that case is not a winning case, give forming row or quote a try
            } // it cannot form a column and a row. Check if it can form a quote
            if (bucket[first_no_empty] >= 2){
                if (quote_num == 0){ // this case has no quote yet
                    int[] new_array = copy_arr(bucket);
                    new_array[first_no_empty] -= 2;
                    quote_num++;
                    int new_quote_num = check_case(quote_num, new_array); // after the column is deleted, is the new_array a possible winning case
                    if (new_quote_num == 1) return 1; // if that case is not a winning case, give forming row or quote a try
                }
                else return -1; // not 0: return an invalid quote number -1
            }
            return -1; // it cannot form a column, row or a quote, so return -1
        }

    }
}