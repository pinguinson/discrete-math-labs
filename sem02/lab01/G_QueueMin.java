
import java.util.*;
import java.io.*;

public class G_QueueMin {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        /* MAIN CODE GOES HERE!!! */
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < k; i++) {
            x[i] = in.nextInt();
        }
        for (int i = k; i < n; i++) {
            x[i] = a * x[i-2] + b * x[i-1] + c;
        }

        long ans = 0;
        MyQueueOnMyLinkedList q = new MyQueueOnMyLinkedList();
        for (int i = 0; i < m; i++) {
            q.push(x[i]);
        }
        ans += q.min();
        for (int i = 0; i < n - m; i++) {
            q.pop();
            q.push(x[m + i]);
            ans += q.min();
        }

        out.println(ans);
    }

    public void run() {
        try {
            in = new FastScanner(new File("queuemin2.in"));
            out = new PrintWriter(new File("queuemin2.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyLinkedList {

        class MyNode {

            public int value;
            public int min;
            MyNode next;

            public MyNode(int x, int y){
                value = x;
                min = y;
                next = null;
            }
        }

        MyNode head;
        private int size;

        public MyLinkedList() {
            head = null;
            size = 0;
        }

        public void insertHead(int x, int y) {
            MyNode n = new MyNode(x, y);
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

        public int topValue() {
            return head.value;
        }

        public int topMin() {
            return head.min;
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
        int minValue;

        public MyStackOnMyLinkedList() {
            st = new MyLinkedList();
        }

        public void push(int value) {
            if (isEmpty()) {
                minValue = value;
            } else {
                minValue = Math.min(value, minValue);
            }
            st.insertHead(value, minValue);
        }

        public int pop() {
            return st.removeHead();
        }

        public int size() {
            return st.size();
        }

        public boolean isEmpty() {
            return st.isEmpty();
        }

        public int min() {
            return st.topMin();
        }
    }

    class MyQueueOnMyLinkedList {

        MyStackOnMyLinkedList s1 = new MyStackOnMyLinkedList();
        MyStackOnMyLinkedList s2 = new MyStackOnMyLinkedList();

        public MyQueueOnMyLinkedList(){
            s1 = new MyStackOnMyLinkedList();
            s2 = new MyStackOnMyLinkedList();
        }
        public void push(int value) {
            s1.push(value);
        }

        public int pop() {
            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }

        public boolean isEmpty() {
            return (s1.isEmpty() && s2.isEmpty());
        }

        public int size() {
            return (s1.size() + s2.size());
        }

        public int min() {
            if (s1.isEmpty()) {
                return s2.min();
            } else if (s2.isEmpty()) {
                return s1.min();
            } else {
                return Math.min(s1.min(), s2.min());
            }
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
        new G_QueueMin().run();
    }
}