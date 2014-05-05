import java.util.*;
import java.io.*;

public class D_QueueOnLinkedList {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        /* MAIN CODE GOES HERE!!! */
        MyQueueOnMyLinkedList q = new MyQueueOnMyLinkedList();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (s.charAt(0) == '-') {
                out.println(q.pop());
            } else if (s.charAt(0) == '+') {
                q.push(in.nextInt());
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("queue2.in"));
            out = new PrintWriter(new File("queue2.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyNode {

        public int value;
        MyNode next;

        public MyNode(int x){
            value = x;
            next = null;
        }
    }

    class MyLinkedList {

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

        public void insertHead(MyNode newHead) {
            newHead.next = head;
            head = newHead;
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

        public int removeAfter(MyNode node) {
            if (node.next == null) {
                throw new IndexOutOfBoundsException();
            }
            MyNode obsolete = node.next;
            int ans = obsolete.value;
            node.next = obsolete.next;
            obsolete = null;
            size--;
            return ans;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    class MyQueueOnMyLinkedList {

        MyLinkedList list = new MyLinkedList();
        MyNode tail = new MyNode(0);

        public MyQueueOnMyLinkedList(){
            list = new MyLinkedList();
        }

        public void push(int value) {
            MyNode newNode = new MyNode(value);
            if (isEmpty()) {
                list.insertHead(newNode);
            } else {
                list.insertAfter(tail, newNode);
            }
            tail = newNode;
        }

        public int pop() {
            return (list.removeHead());
        }

        public boolean isEmpty() {
            return (list.isEmpty());
        }

        public int size() {
            return (list.size());
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
        new D_QueueOnLinkedList().run();
    }
}