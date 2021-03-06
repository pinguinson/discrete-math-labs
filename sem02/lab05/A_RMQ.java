import java.util.*;
import java.io.*;

public class A_RMQ {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int size = in.nextInt();
        long a[] = new long[size];
        for (int i = 0; i < size; i++) {
            a[i] = in.nextInt();
        }
        SegmentTree st = new SegmentTree(size, a);
        try {
            while (true) {
                String s = in.next();
                switch (s) {
                    case "set":
                        st.set(in.nextInt() - 1, in.nextInt());
                        break;
                    case "min":
                        out.println(st.min(in.nextInt() - 1, in.nextInt() - 1));
                        break;
                }
            }
        } catch (NullPointerException e) {
            return;
        }
    }

    class SegmentTree {
        private int SIZE;
        private long array[];
        private long tree[];

        public SegmentTree(int n, long[] a) {
            this.SIZE = n;
            array = a;
            tree = new long[4 * SIZE];
            Arrays.fill(tree, Integer.MAX_VALUE);
            build(1, 0, SIZE - 1);
        }

        private void build(int cur, int left, int right) {
            if (left == right) {
                tree[cur] = array[left];
            } else {
                int mid = (left + right) / 2;
                build(2 * cur, left, mid);
                build(2 * cur + 1, mid + 1, right);
                tree[cur] = Math.min(tree[2 * cur], tree[2 * cur + 1]);
            }
        }

        private void update(int cur, int left, int right, int index, int value) {
            if (left == right) {
                tree[cur] = value;
            } else {
                int mid = (left + right) / 2;
                if (index <= mid) {
                    update(2 * cur, left, mid, index, value);
                } else {
                    update(2 * cur + 1, mid + 1, right, index, value);
                }
                tree[cur] = Math.min(tree[2 * cur], tree[2 * cur + 1]);
            }
        }

        public void set(int index, int value) {
            update(1, 0, SIZE - 1, index, value);
        }

        private long getMin(int cur, int left, int right, int i, int j) {
            if (i > j) {
                return Integer.MAX_VALUE;
            }
            if (i == left && j == right) {
                return tree[cur];
            }
            int mid = (left + right) / 2;
            return Math.min(getMin(2 * cur, left, mid, i, Math.min(j, mid)),
                    getMin(2 * cur + 1, mid + 1, right, Math.max(i, mid + 1), j));
        }

        public long min(int left, int right) {
            return getMin(1, 0, SIZE - 1, left, right);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("rmq.in"));
            out = new PrintWriter(new File("rmq.out"));

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
        new A_RMQ().run();
    }
}