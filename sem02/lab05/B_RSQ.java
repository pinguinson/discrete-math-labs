import java.util.*;
import java.io.*;

public class B_RSQ {
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
                    case "sum":
                        out.println(st.sum(in.nextInt() - 1, in.nextInt() - 1));
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
            Arrays.fill(tree, 0);
            build(1, 0, SIZE - 1);
        }

        private void build(int cur, int left, int right) {
            if (left == right) {
                tree[cur] = array[left];
            } else {
                int mid = (left + right) / 2;
                build(2 * cur, left, mid);
                build(2 * cur + 1, mid + 1, right);
                tree[cur] = tree[2 * cur] + tree[2 * cur + 1];
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
                tree[cur] = tree[2 * cur] + tree[2 * cur + 1];
            }
        }

        public void set(int index, int value) {
            update(1, 0, SIZE - 1, index, value);
        }

        private long getSum(int cur, int left, int right, int i, int j) {
            if (i > j) {
                return 0;
            }
            if (i == left && j == right) {
                return tree[cur];
            }
            int mid = (left + right) / 2;
            return getSum(2 * cur, left, mid, i, Math.min(j, mid)) +
                    getSum(2 * cur + 1, mid + 1, right, Math.max(i, mid + 1), j);
        }

        public long sum(int left, int right) {
            return getSum(1, 0, SIZE - 1, left, right);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("rsq.in"));
            out = new PrintWriter(new File("rsq.out"));

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
        new B_RSQ().run();
    }
}