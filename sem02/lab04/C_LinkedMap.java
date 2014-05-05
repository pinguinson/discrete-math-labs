
import java.util.*;
import java.io.*;

public class C_LinkedMap {
    FastScanner in;
    PrintWriter out;
    public void solve() throws NullPointerException {
        try {
            LinkedMap map = new LinkedMap();
            while (true) {
                String s = in.next();
                switch (s) {

                    case "put":
                        map.put(in.next(), in.next());
                        break;
                    case "prev":
                        out.println(map.prev(in.next()));
                        break;
                    case "deleteAll":
                        map.delete(in.next());
                        break;
                    case "get":
                        out.println(map.get(in.next()));
                        break;
                    case "next":
                        out.println(map.next(in.next()));
                        break;
                }
            }
        } catch (NullPointerException e) {
            return;
        }
    }

    public class LinkedMap {

        private Node hashTable[] = new Node[100_000];
        public Node lastAdded = null;

        private void addNode(Node newNode) {
            if (lastAdded == null)
                lastAdded = newNode;
            else {
                newNode.prevAdded = lastAdded;
                lastAdded.nextAdded = newNode;
                lastAdded = newNode;
            }
        }

        private void removeNode(Node delNode) {
            if (delNode.nextAdded != null)
                delNode.nextAdded.prevAdded = delNode.prevAdded;
            if (delNode.prevAdded != null)
                delNode.prevAdded.nextAdded = delNode.nextAdded;
            if (lastAdded.key.equals(delNode.key))
                lastAdded = delNode.prevAdded;
        }

        public void put(String key, String value) {
            int index = hash(key);
            Node cur = hashTable[index];
            while (cur != null) {
                if (cur.key.equals(key)) {
                    cur.value = value;
                    return;
                }
                cur = cur.next;
            }
            Node newNode = new Node(key, value);
            newNode.next = hashTable[index];
            hashTable[index] = newNode;
            addNode(newNode);
        }

        public void delete(String key) {
            int index = hash(key);
            Node cur = hashTable[index];
            if (cur != null) {
                if (cur.key.equals(key)) {
                    removeNode(cur);
                    hashTable[index] = hashTable[index].next;
                } else {
                    while (cur.next != null) {
                        if (cur.next.key.equals(key)) {
                            removeNode(cur.next);
                            cur.next = cur.next.next;
                            return;
                        }
                        cur = cur.next;
                    }
                }
            }
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

        public String prev(String key) {
            Node cur = hashTable[hash(key)];
            while (cur != null && !cur.key.equals(key)) {
                cur = cur.next;
            }
            if (cur == null) {
                return "none";
            }
            if (cur.prevAdded == null) {
                return "none";
            }
            return cur.prevAdded.value;
        }

        public String next(String key) {
            Node cur = hashTable[hash(key)];
            while (cur != null && !cur.key.equals(key)) {
                cur = cur.next;
            }
            if (cur == null) {
                return "none";
            }
            if (cur.nextAdded == null) {
                return "none";
            }
            return cur.nextAdded.value;
        }

        public int hash(String s) {
            int ans = 7;
            for (int i = 0; i < s.length(); i++) {
                ans = Math.abs(ans * 127 + s.charAt(i) - 'A' + 1) % 100_000;
            }
            return ans;
        }

    }

    public class Node {
        public Node next;
        public Node prevAdded;
        public Node nextAdded;
        public String key;
        public String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("linkedmap.in"));
            out = new PrintWriter(new File("linkedmap.out"));
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
        new C_LinkedMap().run();
    }
}