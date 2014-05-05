import java.util.*;
import java.io.*;

public class E_Parking {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {

        int n = in.nextInt();
        int m = in.nextInt();

        int a[] = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = i;
        }

        SegmentTree st = new SegmentTree(n+1, a);

        for (int i = 0; i < m; i++) {
            String s = in.next();
            int pos = in.nextInt();
            switch (s) {
                case "enter":
                    out.println(st.enter(pos));
                    break;
                case "exit":
                    st.exit(pos);
                    break;
            }
        }
    }

    class SegmentTree {
        private int SIZE;
        private int array[];
        private int tree[];

        public SegmentTree(int n, int[] a) {
            this.SIZE = n - 1;
            array = a;
            tree = new int[4 * SIZE];
            Arrays.fill(tree, Integer.MAX_VALUE);
            build(1, 1, SIZE);
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
            update(1, 1, SIZE, index, value);
        }

        private int getMin(int cur, int left, int right, int i, int j) {
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

        public int min(int left, int right) {
            return getMin(1, 1, SIZE, left, right);
        }

        public int enter(int pos) {
            int index = min(pos, SIZE);
            if (index == Integer.MAX_VALUE) {
                index = min(1, pos - 1);
            }
            set(index, Integer.MAX_VALUE);
            return index;
        }

        public void exit(int pos) {
            set(pos, pos);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("parking.in"));
            out = new PrintWriter(new File("parking.out"));

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
        new E_Parking().run();
    }
}