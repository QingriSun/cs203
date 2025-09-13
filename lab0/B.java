import java.io.*;
import java.util.*;

public class B
{
    public static void main(String[] argv)
    {
        QReader reader = new QReader();
        QWriter writer = new QWriter();

        // build a bucket to held all possible input number value
        int a_len = reader.nextInt();

        // loop through the a_arr to put each number into the bucket
        int[] arr = new int[100001];
        for (int i = 0; i < a_len; i++)
        {
            arr[reader.nextInt()]++;
        }

        // judge if each b's associated bucket is empty or not
        int b_len = reader.nextInt();
        int[] in_a = new int[b_len];
        for (int i = 0; i < b_len; i++)
        {
            if (arr[reader.nextInt()] != 0)
            {
                in_a[i]++;
            }
        }

        // print the output
        for (int i = 0; i< b_len; i++)
        {
            if (in_a[i] == 0)
            {
                writer.println("no");
            }
            else
            {
                writer.println("yes");
            }
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
 
    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}