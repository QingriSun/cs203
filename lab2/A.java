import java.io.*;
import java.util.*;

public class A{
    public static void main(String[] argv){
        // read input
        QReader scanner = new QReader();
        int test_num = scanner.nextInt();

        int[][][] test = new int[test_num][2][];
        for (int j = 0; j < test_num; j++){
            int a1_len = scanner.nextInt();
            int a2_len = scanner.nextInt();
            int[] a1 = new int[a1_len];
            int[] a2 = new int[a2_len];
            for (int i = 0; i < a1_len; i++){
                a1[i] = scanner.nextInt();
            }
            for (int i = 0; i < a2_len; i++){
                a2[i] = scanner.nextInt();
            } 
            test[j][0] = a1;
            test[j][1] = a2;
        }

        // merge
        int p1;
        int p2;
        int p3;
        int[][] merged = new int[test_num][];
        for (int i = 0; i < test_num; i++){
            p1 = 0;
            p2 = 0;
            p3 = 0;
            int a1_len = test[i][0].length;
            int a2_len = test[i][1].length;
            merged[i] = new int[a1_len + a2_len];
            while (p1 < a1_len && p2 < a2_len){
                if (test[i][0][p1] >= test[i][1][p2]){
                    merged[i][p3] = test[i][1][p2];
                    p2++;
                }
                else{
                    merged[i][p3] = test[i][0][p1];
                    p1++;
                }
                p3++;
            }

            // put the remaining elements into the output array
            if (p1 < a1_len){ // p2 = a2_len
                for (; p1 < a1_len;){
                    merged[i][p3++] = test[i][0][p1++];
                }
            }
            else{ // p1 = a1_len
                for (; p2 < a2_len;){
                    merged[i][p3++] = test[i][1][p2++];
                }
            }
        }

        QWriter writer = new QWriter();
        for (int i = 0; i < test_num; i++){
            for (int j = 0; j < merged[i].length; j++){
                writer.printf(merged[i][j]);
                writer.printf(" ");
            }
            writer.printf("\n");
        }
        writer.close();

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

    public void printf(Object object){
        try {
            writer.write(object.toString());      
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