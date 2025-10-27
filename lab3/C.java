import java.io.*;
import java.util.*;

public class C{
    public static void main(String[] argv){
        QReader reader = new QReader();
        int test_num = reader.nextInt();

        String output[] = new String[test_num];

        for (int i = 0; i < test_num; i++){
            int len = reader.nextInt();
            int order_num = reader.nextInt();

            // create the link list of line
            Node head = new Node(len);
            Node tail = new Node(-1);
            head.next = tail;
            tail.front = head;
            Node c = head;
            Node nodes[] = new Node[len];

            for (int j = 0; j < len; j++){
                Node new_node = new Node(reader.nextInt());
                c.next.front = new_node;
                new_node.next = c.next;
                new_node.front = c;
                c.next = new_node;
                c = c.next;
                nodes[c.id] = c;
            }

            for (int j = 0; j < order_num; j++){
                int start1 = reader.nextInt();
                int end1 = reader.nextInt();
                int start2 = reader.nextInt();
                int end2 = reader.nextInt();

                if (nodes[end1].next != nodes[start2]){
                    Node temp1 = nodes[start1].front;
                    Node temp2 = nodes[end1].next;
                    Node temp3 = nodes[start2].front;
                    Node temp4 = nodes[end2].next;

                    temp1.next = nodes[start2];
                    nodes[start2].front = temp1;
                    nodes[end2].next = temp2;
                    temp2.front = nodes[end2];
                    temp3.next = nodes[start1];
                    nodes[start1].front = temp3;
                    nodes[end1].next = temp4;
                    temp4.front = nodes[end1];
                }else{
                    Node temp1 = nodes[start1].front;
                    Node temp2 = nodes[end2].next;

                    temp1.next = nodes[start2];
                    nodes[start2].front = temp1;
                    nodes[end2].next = nodes[start1];
                    nodes[start1].front = nodes[end2];
                    nodes[end1].next = temp2;
                    temp2.front = nodes[end2];
                }
            }

            StringBuffer sb = new StringBuffer();
            c = head.next;
            for (int j = 0; j < len; j++){
                sb.append(c.id);
                sb.append(' ');
                c = c.next;
            }
            output[i] = sb.toString();
        }

        QWriter writer = new QWriter();
        for (int i = 0; i < test_num; i++){
            writer.println(output[i]);
        }
        writer.close();
    }
}

class Node{
    int id;
    Node next;
    Node front;

    Node(int id){
        this.id = id;
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