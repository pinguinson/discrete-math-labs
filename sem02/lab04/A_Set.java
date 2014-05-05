import java.util.*;
import java.io.*;

public class A_Set {
    FastScanner in;
    PrintWriter out;
    public void solve() throws NullPointerException {
        try {
            HashSet hash = new HashSet();
            while (true) {
                String s = in.next();
                int val = in.nextInt();
                switch (s.charAt(0)) {
                    case 'i':
                        hash.insert(val);
                        break;
                    case 'd':
                        hash.delete(val);
                        break;
                    case 'e':
                        out.println(hash.exists(val));
                        break;
                }
            }
        } catch (NullPointerException e) {
            return;
        }
    }

    public class HashSet {

        private Node hashTable[] = new Node[1_000_000];

        public void insert(int x) {
            if (exists(x)) {
                return;
            }
            Node buff = hashTable[hash(x)];
            hashTable[hash(x)] = new Node(x, buff);
        }

        public boolean exists(int x) {
            Node cur = hashTable[hash(x)];
            while (cur != null && cur.value != x) {
                cur = cur.next;
            }
            return (cur != null);
        }

        public void delete(int x) {
            Node cur = hashTable[hash(x)];
            if (cur == null) {
                return;
            }
            if (cur.value == x) {
                hashTable[hash(x)] = cur.next;
                cur = null;
                return;
            }
            while (cur.next != null && cur.next.value != x) {
                cur = cur.next;
            }
            if (cur.next == null) {
                if (cur.value == x) {
                    hashTable[hash(x)] = null;
                }
                return;
            }
            cur.next = cur.next.next;
        }

        public int hash(int x) {
            return Math.abs(31 * x + 77) % 1_000_000;
        }

    }

    public class Node {
        public Node next;
        public int value;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("set.in"));
            out = new PrintWriter(new File("set.out"));
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        new A_Set().run();
    }
}