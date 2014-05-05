import java.util.*;
import java.io.*;

public class B_Map {
    FastScanner in;
    PrintWriter out;
    public void solve() throws NullPointerException {
        try {
            Map map = new Map();
            while (true) {
                String s = in.next();
                switch (s.charAt(0)) {
                    case 'p':
                        map.put(in.next(), in.next());
                        break;
                    case 'd':
                        map.delete(in.next());
                        break;
                    case 'g':
                        out.println(map.get(in.next()));
                        break;
                }
            }
        } catch (NullPointerException e) {
            return;
        }
    }

    public class Map {

        private Node hashTable[] = new Node[100000];

        public void put(String key, String value) {
            Node cur = hashTable[hash(key)];
            if (cur == null) {
                hashTable[hash(key)] = new Node(key, value, null);
                return;
            }
            while (cur.next != null && !cur.next.key.equals(key)) {
                cur = cur.next;
            }
            if (cur.key.equals(key)) {
                cur.value = value;
                return;
            }
            if (cur.next == null) {
                cur.next = new Node(key, value, null);
                return;
            }
            cur.next.value = value;
        }

        public void delete(String key) {
            Node cur = hashTable[hash(key)];
            if (cur == null) {
                return;
            }
            if (cur.key.equals(key)) {
                hashTable[hash(key)] = cur.next;
                cur = null;
                return;
            }
            while (cur.next != null && !cur.next.key.equals(key)) {
                cur = cur.next;
            }
            if (cur.next == null) {
                if (cur.key.equals(key)) {
                    hashTable[hash(key)] = null;
                }
                return;
            }
            cur.next = cur.next.next;
        }

        public String get(String key) {
            Node cur = hashTable[hash(key)];
            while (cur != null && !cur.key.equals(key)) {
                cur = cur.next;
            }
            if (cur == null) {
                return "none";
            }
            return cur.value;
        }

        public int hash(String s) {
            int ans = 7;
            for (int i = 0; i < s.length(); i++) {
                ans = Math.abs(ans * 127 + s.charAt(i) - 'A' + 1) % 100000;
            }
            return ans;
        }

    }

    public class Node {
        public Node next;
        public String key;
        public String value;

        public Node(String key, String value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("map.in"));
            out = new PrintWriter(new File("map.out"));
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
        new B_Map().run();
    }
}