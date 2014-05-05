import java.util.*;
import java.io.*;

public class D_RMQ_Hard {
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
                int left = in.nextInt() - 1;
                int right = in.nextInt() - 1;
                long value;
                switch (s) {
                    case "set":
                        value = in.nextLong();
                        st.set(left, right, value);
                        break;
                    case "add":
                        value = in.nextLong();
                        st.add(left, right, value);
                        break;
                    case "min":
                        out.println(st.min(left, right));
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
        private long add[];
        private long set[];
        public static final long INF = Long.MAX_VALUE;

        public SegmentTree(int n, long[] a) {
            this.SIZE = n;
            array = a;
            tree = new long[4 * SIZE];
            add = new long[4 * SIZE];
            set = new long[4 * SIZE];
            Arrays.fill(tree, INF);
            Arrays.fill(set, INF);
            build(1, 0, SIZE - 1);
        }

        private void build(int cur, int left, int right) {
            if (left == right) {
                tree[cur] = array[left];
            } else {
                int mid = (left + right) / 2;
                build(2 * cur, left, mid);
                build(2 * cur + 1, mid + 1, right);
                update(cur);
            }
        }

        private void placeAdd(int cur, long value) {
            tree[cur] += value;
            if (set[cur] != INF) {
                set[cur] += value;
            } else {
                add[cur] += value;
            }
        }

        private void placeSet(int cur, long value) {
            tree[cur] = set[cur] = value;
            add[cur] = 0;
        }

        private void push(int cur) {
            if (add[cur] != 0) {
                placeAdd(2 * cur, add[cur]);
                placeAdd(2 * cur + 1, add[cur]);
                add[cur] = 0;
            }
            if (set[cur] != INF) {
                placeSet(2 * cur, set[cur]);
                placeSet(2 * cur + 1, set[cur]);
                set[cur] = INF;
            }
        }

        private void update(int cur) {
            tree[cur] = Math.min(tree[2 * cur], tree[2 * cur + 1]);
        }

        private void addInRange(int cur, int left, int right, int i, int j, long value) {
            if (i > j) {
                return;
            }
            if (i == left && j == right) {
                placeAdd(cur, value);
            } else {
                push(cur);
                int mid = (left + right) / 2;
                addInRange(2 * cur, left, mid, i, Math.min(j, mid), value);
                addInRange(2 * cur + 1, mid + 1, right, Math.max(i, mid + 1), j, value);
                update(cur);
            }
        }

        private void setInRange(int cur, int left, int right, int i, int j, long value) {
            if (i > j) {
                return;
            }
            if (i == left && j == right) {
                placeSet(cur, value);
            } else {
                push(cur);
                int mid = (left + right) / 2;
                setInRange(2 * cur, left, mid, i, Math.min(j, mid), value);
                setInRange(2 * cur + 1, mid + 1, right, Math.max(i, mid + 1), j, value);
                update(cur);
            }
        }

        private long getMin(int cur, int left, int right, int i, int j) {
            if (i > j) {
                return INF;
            }
            if (i == left && j == right) {
                return tree[cur];
            }
            push(cur);
            int mid = (left + right) / 2;
            return Math.min(getMin(2 * cur, left, mid, i, Math.min(j, mid)),
                            getMin(2 * cur + 1, mid + 1, right, Math.max(i, mid + 1), j));
        }

        public void add(int left, int right, long value) {
            addInRange(1, 0, SIZE - 1, left, right, value);
        }

        public void set(int left, int right, long value) {
            setInRange(1, 0, SIZE - 1, left, right, value);
        }

        public long min(int left, int right) {
            return getMin(1, 0, SIZE - 1, left, right);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("rmq2.in"));
            out = new PrintWriter(new File("rmq2.out"));

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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] arg) {
        new D_RMQ_Hard().run();
    }
}