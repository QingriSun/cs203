import java.io.*;
import java.util.*;

public class FTest{
    public static void main(String[] argv){
        Random random = new Random();
        QWriter writer = new QWriter();
        int case_num = (int) 2e5; // random.nextInt((int) 2e5) + 1;

        System.out.println(case_num + " " + 20 + " " + (int) 2e5 + "\n");
        for (int i = 0; i < case_num; i++){
            System.out.println((int) 1e9 + " " + 1);
        }

        /* 
        System.out.println(case_num + " " + random.nextInt(21) + " " + random.nextInt((int) 2e5 + 1) + "\n");
        for (int i = 0; i < case_num; i++){
            System.out.println((random.nextInt((int) 1e9) + 1) + " " + (random.nextInt((int) 1e9) + 1));
        }
        */
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