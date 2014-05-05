import java.util.*;
import java.io.*;

//pinguinson

public class C_Crypto {
    static FastScanner in;
    static PrintWriter out;

    public void solve() throws IOException {
        int r = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        Matrix a[] = new Matrix[n];

        for (int i = 0; i < n; i++) {
            a[i] = new Matrix(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
        }

        SegmentTree st = new SegmentTree(n, a, r);
        for (int i = 0; i < m; i++) {
            st.product(in.nextInt() - 1, in.nextInt() - 1).print();
        }
    }

    static class Matrix {
        int[][] x = new int[2][2];

        public Matrix(int x1, int x2, int x3, int x4) {
            x[0][0] = x1;
            x[0][1] = x2;
            x[1][0] = x3;
            x[1][1] = x4;
        }

        public void print() {
            out.print(x[0][0] + " ");
            out.println(x[0][1]);
            out.print(x[1][0] + " ");
            out.println(x[1][1]);
            out.println();
        }
    }

    public static Matrix multiply(Matrix a, Matrix b, int r) {
        //1st row * 1st column
        int x1 = (a.x[0][0] * b.x[0][0] + a.x[0][1] * b.x[1][0]) % r;
        int x2 = (a.x[0][0] * b.x[0][1] + a.x[0][1] * b.x[1][1]) % r;
        int x3 = (a.x[1][0] * b.x[0][0] + a.x[1][1] * b.x[1][0]) % r;
        int x4 = (a.x[1][0] * b.x[0][1] + a.x[1][1] * b.x[1][1]) % r;
        return new Matrix(x1, x2, x3, x4);
    }

    class SegmentTree {
        private int SIZE;
        private Matrix array[];
        private Matrix tree[];
        private int radix;

        public SegmentTree(int n, Matrix[] a, int radix) {
            this.SIZE = n;
            this.array = a;
            this.radix = radix;
            this.tree = new Matrix[4 * SIZE];
            Arrays.fill(tree, new Matrix(1, 0, 0, 1));
            build(1, 0, SIZE - 1);
        }

        private void build(int cur, int left, int right) {
            if (left == right) {
                tree[cur] = array[left];
            } else {
                int mid = (left + right) / 2;
                build(2 * cur, left, mid);
                build(2 * cur + 1, mid + 1, right);
                tree[cur] = multiply(tree[2 * cur], tree[2 * cur + 1], radix);
            }
        }

        private Matrix getProduct(int cur, int left, int right, int l, int r) {
            if (l > r) {
                return new Matrix(1, 0, 0, 1);
            }
            if (l == left && r == right) {
                return tree[cur];
            }
            int mid = (left + right) / 2;
            return multiply(getProduct(2 * cur, left, mid, l, Math.min(r, mid)),
                    getProduct(2 * cur + 1, mid + 1, right, Math.max(l, mid + 1), r), radix);
        }

        public Matrix product(int left, int right) {
            return getProduct(1, 0, SIZE - 1, left, right);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("crypto.in"));
            out = new PrintWriter(new File("crypto.out"));

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
        new C_Crypto().run();
    }
}