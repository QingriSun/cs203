import java.io.*;
import java.util.*;


public class A {
    public static void main(String[] argv){
        QReader in = new QReader();
        int len1 = in.nextInt();
        int len2 = in.nextInt();

        Node head1 = new Node((int) 2e9, (int) 2e9);
        Node tail1=head1; // an address
        head1.next = tail1;
        for (int i = 0; i < len1; i++){
            Node temp = new Node(in.nextInt(), in.nextInt());
            tail1.next=temp;
            tail1=temp;
        }
        tail1.next=null;
        Node head2 = new Node((int) 2e9, (int) 2e9);
        Node tail2=head2; // an address
        head2.next = tail2;
        tail2.next=head2;
        for (int i = 0; i < len2; i++){
            Node temp = new Node(in.nextInt(), in.nextInt());
            tail2.next=temp;
            tail2=temp;
        }
        tail2.next=null;

        Node head3 = new Node((int) 2e9, (int) 2e9);
        Node cur = head3;
        Node cur1=head1.next;
        Node cur2=head2.next;
        int count = 0;
        int count1 = 0;
        int count2 = 0;
        
        while (cur1!=null && cur2!=null){
            if (cur1.exp==cur2.exp){
                Node temp = new Node(cur1.coe+cur2.coe, cur1.exp);
                cur.next=temp;
                cur=temp;
                cur1=cur1.next;
                cur2=cur2.next;
                count1++;
                count2++;
            }else if (cur1.exp>cur2.exp){
                Node temp = new Node(cur1.coe, cur1.exp);
                cur.next=temp;
                cur=temp;
                cur1=cur1.next;
                count1++;
            }else{
                Node temp = new Node(cur2.coe, cur2.exp);
                cur.next=temp;
                cur=temp;
                cur2=cur2.next;
                count2++;
            }
            count++;
        }
        cur.next=null;

        if (cur1!=null){
            cur.next=cur1;
            count+=(len1-count1);
        }else{
            cur.next=cur2;
            count+=(len2-count2);
        }

        QWriter writer = new QWriter();
        writer.println(count);
        cur=head3.next;
        while (cur!=null){
            writer.print(cur.coe);
            writer.print(' ');
            writer.print(cur.exp);
            writer.print('\n');
            cur=cur.next;
        }
        writer.close();
        
    }
}

class Node{
    int coe;
    int exp;
    Node next;

    public Node(int coe, int exp){
        this.coe = coe;
        this.exp = exp;
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
