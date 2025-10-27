import java.io.*;
import java.util.*;

public class E{
    public static void main(String[] argv){

        // read the input
        QReader scanner = new QReader();
        int arr_len = scanner.nextInt();
        int[] arr = new int[arr_len];
        for (int j = 0; j < arr_len; j++){
            arr[j] = scanner.nextInt();
        }

        arr = sort(arr, 0, arr.length - 1);

        QWriter writer = new QWriter();
        int[] new_arr = new int[arr.length];
        writer.println(arr[arr.length / 3]);

        int period_num = arr.length / 3;
        int full_of_3;
        int period;
        int new_index;
        for (int i = 0; i < arr.length / 3; i++){
            full_of_3 = i / period_num;
            period = i % period_num;
            new_index = period * 3 + full_of_3;
            new_arr[new_index] = arr[i];
        }
        int except_first_in_3;
        int full_of_2;
        int position_in_2;
        for (int i = arr.length / 3; i < arr.length / 3 * 3; i++){
            except_first_in_3 = i - period_num;
            full_of_2 = except_first_in_3 / 2;
            position_in_2 = except_first_in_3 % 2;
            new_index = 1 + full_of_2 * 3 + position_in_2;
            new_arr[new_index] = arr[i];
        }
        for (int i = arr.length / 3 * 3; i < arr.length; i++){
            new_arr[i] = arr[i];
        }

        for (int i = 0; i < arr.length - 1; i++){
            writer.print(new_arr[i]);
            writer.print(" ");
        }
        writer.print(new_arr[arr.length - 1]);
        writer.print("\n");
        writer.close();

    }

    private static int[] sort(int[] arr, int L, int R){
        if (R - L == 0){
            return new int[] {arr[L]};
        }else if (R - L == 1){
            if (arr[L] > arr[R]){
                return new int[] {arr[R], arr[L]};
            } 
            else return new int[] {arr[L], arr[R]}; 
        }else{
            int mid  = R - (R - L) / 2;
            int[] L_arr = sort(arr, L, mid - 1);
            int[] R_arr = sort(arr, mid, R);

            return merge(L_arr, R_arr);
        }
    }

    private static int[] merge(int[] L, int[] R){
        int[] new_arr = new int[L.length + R.length];
        int L_pnt = 0;
        int R_pnt = 0;
        int sorted = 0;
        while (L_pnt < L.length && R_pnt < R.length){
            if (L[L_pnt] <= R[R_pnt]) new_arr[sorted++] = L[L_pnt++];
            else{
                new_arr[sorted++] = R[R_pnt++];
            }
        }

        for (; L_pnt < L.length;){
            new_arr[sorted++] = L[L_pnt++];
        }
        for (; R_pnt < R.length;){
            new_arr[sorted++] = R[R_pnt++];
        }

        return new_arr;
    }
    
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");
 
    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
 
    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }
 
    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }
 
    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }
 
    public int nextInt() {
        return Integer.parseInt(next());
    }
 
    public long nextLong() {
        return Long.parseLong(next());
    }
}
 
class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
 
    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }
 
    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }
 
    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}