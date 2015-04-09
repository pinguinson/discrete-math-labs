import java.util.*;
import java.io.*;

public class C {
    static FastScanner in;
    static PrintWriter out;
    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789\1\2";

    public void solve() throws IOException {
        String s = in.next();
        Node tree = buildSuffixTree(s);
        tree.print();
    }

    public static class Node {
        int begin;
        int end;
        int depth; // distance in characters from root to this node
        Node parent;
        Node[] children;
        Node suffixLink;
        int index;

        Node(int begin, int end, int depth, Node parent, int index) {
            this.begin = begin;
            this.end = end;
            this.parent = parent;
            this.depth = depth;
            children = new Node[ALPHABET.length()];
            this.index = index;
        }

        void print() {
            if (this.parent != null) {
                out.println(this.parent.index + " " + this.index + " " + (this.begin + 1) + " " + this.end);
            }
            if (this.children != null && this.children.length > 0) {
                for (Node child : children) {
                    if (child != null)
                        child.print();
                }
            }
        }
    }

    public static Node buildSuffixTree(CharSequence s) {
        int n = s.length();
        int curIndex = 1;
        byte[] a = new byte[n];
        for (int i = 0; i < n; i++) a[i] = (byte) ALPHABET.indexOf(s.charAt(i));
        Node root = new Node(0, 0, 0, null, curIndex++);
        Node node = root;
        for (int i = 0, tail = 0; i < n; i++, tail++) {
            Node last = null;
            while (tail >= 0) {
                Node ch = node.children[a[i - tail]];
                while (ch != null && tail >= ch.end - ch.begin) {
                    tail -= ch.end - ch.begin;
                    node = ch;
                    ch = ch.children[a[i - tail]];
                }
                if (ch == null) {
                    node.children[a[i]] = new Node(i, n, node.depth + node.end - node.begin, node, curIndex++);
                    if (last != null) last.suffixLink = node;
                    last = null;
                } else {
                    byte t = a[ch.begin + tail];
                    if (t == a[i]) {
                        if (last != null) last.suffixLink = node;
                        break;
                    } else {
                        Node splitNode = new Node(ch.begin, ch.begin + tail, node.depth + node.end - node.begin, node, curIndex++);
                        splitNode.children[a[i]] = new Node(i, n, ch.depth + tail, splitNode, curIndex++);
                        splitNode.children[t] = ch;
                        ch.begin += tail;
                        ch.depth += tail;
                        ch.parent = splitNode;
                        node.children[a[i - tail]] = splitNode;
                        if (last != null) last.suffixLink = splitNode;
                        last = splitNode;
                    }
                }
                if (node == root) {
                    --tail;
                } else {
                    node = node.suffixLink;
                }
            }
        }
        out.println((curIndex - 1) + " " + (curIndex - 2));
        return root;
    }

    public void run() {
        try {
            in = new FastScanner(new File("tree.in"));
            out = new PrintWriter(new File("tree.out"));

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

    public static void main(String[] arg) {
        new C().run();
    }
}