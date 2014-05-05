import java.util.*;
import java.io.*;

public class B_StackOnLinkedList {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        /* MAIN CODE GOES HERE!!! */
        MyStackOnMyLinkedList stack = new MyStackOnMyLinkedList();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (s.charAt(0) == '-') {
                out.println(stack.pop());
            } else if (s.charAt(0) == '+') {
                stack.push(in.nextInt());
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("stack2.in"));
            out = new PrintWriter(new File("stack2.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyLinkedList {

        class MyNode {

            int value;
            MyNode next;

            public MyNode(int x){
                value = x;
                next = null;
            }
        }

        MyNode head;
        private int size;

        public MyLinkedList() {
            head = null;
            size = 0;
        }

        public void insertHead(int x) {
            MyNode n = new MyNode(x);
            n.next = head;
            head = n;
            size++;
        }

        public int removeHead() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }

            MyNode prev = head;
            int ans = prev.value;
            head = prev.next;
            prev = null;
            size--;
            return ans;
        }

        public void insertAfter(MyNode after, MyNode newNode) {
            newNode.next = after.next;
            after.next = newNode;
            size++;
        }

        public void removeAfter(MyNode node) {
            if (node.next == null) {
                throw new IndexOutOfBoundsException();
            }
            MyNode obsolete = node.next;
            node.next = obsolete.next;
            obsolete = null;
            size--;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    class MyStackOnMyLinkedList {

        MyLinkedList st = new MyLinkedList();

        public MyStackOnMyLinkedList() {
            st = new MyLinkedList();
        }

        public void push(int value) {
            st.insertHead(value);
        }

        public int pop() {
            return st.removeHead();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] arg) {
        new B_StackOnLinkedList().run();
    }
}