import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] argv){

        QReader reader = new QReader();
        QWriter writer = new QWriter();
        int test_num = reader.nextInt();

        // store the output
        String output[] = new String[test_num];


        for (int i = 0; i < test_num; i++){

            int len = reader.nextInt();
            String str = reader.nextLine();

            Node head = new Node(10);
            Node tail = new Node(-1); // representing EOL using -1
            tail.front = head;
            tail.next = tail; // right shift on tail is still tail
            head.next = tail;
            Node current = tail;

            for (int j = 0; j < len; j++){

                char c = str.charAt(j);

                switch (c) {
                    case 'H':
                        current = (current.front.digit == 10) ? current : current.front;
                        break;
                    case 'L':
                        current = current.next;
                        break;
                    case 'I':
                        current = head.next;
                        break;
                    case 'r':
                        if (current.digit != -1 && j + 1 < len) current.digit = (str.charAt(++j) - '0');
                        else if (current.digit == -1){
                            insert(new Node(str.charAt(++j) - '0'), current);
                            current = current.front;
                        } 
                        break;
                    case 'x':
                        if (current.digit != -1){
                            current.front.next = current.next;
                            current.next.front = current.front;
                            current = current.next;
                        }
                        break;
                    default:
                        Node n = new Node(c - '0');
                        insert(n, current);
                        break;
                }
            }

            StringBuffer out_str = new StringBuffer();

            current = head.next;
            while (current.digit != -1){
                out_str.append(current.digit);
                current = current.next;
            }
            output[i] = out_str.toString();
        }

        for (int i = 0; i < test_num; i++){
            writer.println(output[i]);
        }
        writer.close();
    }

    private static void insert(Node n, Node current){
        current.front.next = n;
        n.front = current.front;
        n.next = current;
        current.front = n;
    }
}

class Node {
    int digit;
    Node front;
    Node next;

    Node (int digit){
        this.digit = digit;
    }
    Node(){}
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
            System.out.println("IOException");
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