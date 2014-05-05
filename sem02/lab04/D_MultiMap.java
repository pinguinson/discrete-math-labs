import java.util.*;
import java.io.*;

public class D_MultiMap {
    FastScanner in;
    PrintWriter out;
    public void solve() throws NullPointerException {
        try {
            MultiMap multiMap = new MultiMap();
            while (true) {
                String s = in.next();
                switch (s) {
                    case "put":
                        multiMap.put(in.next(), in.next());
                        break;
                    case "deleteall":
                        multiMap.deleteAll(in.next());
                        break;
                    case "delete":
                        multiMap.delete(in.next(), in.next());
                        break;
                    case "get":
                        out.println(multiMap.get(in.next()));
                        break;
                }
            }
        } catch (NullPointerException e) {
            return;
        }
    }

    public class MultiMap {

        private MapNode hashTable[] = new MapNode[100_000];

        public void put(String key, String value) {
            MapNode cur = hashTable[hash(key)];
            if (cur == null) {
                HashSet hashSet = new HashSet();
                hashSet.insert(value);
                hashTable[hash(key)] = new MapNode(key, hashSet, null);
                return;
            }
            if (cur.key.equals(key)) {
                cur.value.insert(value);
                return;
            }
            while (cur.next != null && !cur.next.key.equals(key)) {
                cur = cur.next;
            }
            if (cur.next == null) {
                HashSet hashSet = new HashSet();
                hashSet.insert(value);
                cur.next = new MapNode(key, hashSet, null);
                return;
            }
            cur.next.value.insert(value);
        }

        public void deleteAll(String key) {
            MapNode cur = hashTable[hash(key)];
            if (cur == null) {
                return;
            }
            if (cur.key.equals(key)) {
                hashTable[hash(key)] = cur.next;
                return;
            }
            while (cur.next != null && !cur.next.key.equals(key)) {
                cur = cur.next;
            }
            if (cur.next != null) {
                cur.next = cur.next.next;
            }
        }

        public void delete(String key, String value) {
            MapNode cur = hashTable[hash(key)];
            if (cur == null) {
                return;
            }
            if (cur.key.equals(key)) {
                cur.value.delete(value);
                return;
            }
            while (cur.next != null && !cur.next.key.equals(key)) {
                cur = cur.next;
            }
            if (cur.next != null) {
                cur.next.value.delete(value);
            }
        }

        public String get(String key) {
            StringBuilder sb = new StringBuilder();
            MapNode cur = hashTable[hash(key)];
            while (cur != null && !cur.key.equals(key)) {
                cur = cur.next;
            }
            if (cur == null) {
                return "0";
            }
            HashSet set = cur.value;
            sb.append(Integer.toString(set.size));
            sb.append(" ");
            sb.append(set.allToString());
            return sb.toString();
        }

        public int hash(String s) {
            int ans = 7;
            for (int i = 0; i < s.length(); i++) {
                ans = Math.abs(ans * 127 + s.charAt(i) - 'A' + 1) % 100_000;
            }
            return ans;
        }

    }

    public class MapNode {
        public MapNode next;
        public String key;
        public HashSet value;

        public MapNode(String key, HashSet value, MapNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public class HashSet {

        private class Node {
            public Node next;
            public String value;

            public Node(String value, Node next) {
                this.value = value;
                this.next = next;
            }
        }

        private final int TABLE_SIZE = 500;
        private Node hashTable[] = new Node[TABLE_SIZE];
        int size = 0;

        public void insert(String value) {
            if (exists(value)) {
                return;
            }
            size++;
            Node buff = hashTable[hash(value)];
            hashTable[hash(value)] = new Node(value, buff);
        }

        public boolean exists(String value) {
            Node cur = hashTable[hash(value)];
            while (cur != null && !cur.value.equals(value)) {
                cur = cur.next;
            }
            return (cur != null);
        }

        public void delete(String value) {
            Node cur = hashTable[hash(value)];
            if (cur == null) {
                return;
            }
            if (cur.value.equals(value)) {
                hashTable[hash(value)] = cur.next;
                size--;
                return;
            }
            while (cur.next != null && !cur.next.value.equals(value)) {
                cur = cur.next;
            }
            if (cur.next != null) {
                cur.next = cur.next.next;
                size--;
            }
        }

        public int hash(String s) {
            int ans = 7;
            for (int i = 0; i < s.length(); i++) {
                ans = Math.abs(ans * 127 + s.charAt(i) - 'A' + 1) % TABLE_SIZE;
            }
            return ans;
        }

        public String allToString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < TABLE_SIZE; i++) {
                Node cur = hashTable[i];
                while (cur != null) {
                    sb.append(cur.value);
                    sb.append(" ");
                    cur = cur.next;
                }
            }
            return sb.toString();
        }
    }



    public void run() {
        try {
            in = new FastScanner(new File("multimap.in"));
            out = new PrintWriter(new File("multimap.out"));
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
        new D_MultiMap().run();
    }
}