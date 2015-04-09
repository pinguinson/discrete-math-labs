import java.util.*;
import java.io.*;

public class A {
    FastScanner in;
    PrintWriter out;

    public static final int INF = 2_000_000;

    int n;
    int[][] a;
    int[] u;
    int[] v;
    int[] p;
    int[] w;
    int [] answer;
    int cost;

    public void solve() throws IOException {
        readInput();
        hungarian();
        printAnswer();
    }

    public void readInput() {
        n = in.nextInt();
        a = new int[n + 1][n + 1];
        u = new int[n + 1];
        v = new int[n + 1];
        p = new int[n + 1];
        w = new int[n + 1];
        answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = in.nextInt();
            }
        }
    }

    public void hungarian() {
        for (int i = 1; i <= n; i++) {
            p[0] = i;
            int j0 = 0;
            int[] minv = new int[n + 1];
            Arrays.fill(minv, INF);
            boolean[] used = new boolean[n + 1];
            Arrays.fill(used, false);
            do {
                used[j0] = true;
                int i0 = p[j0];
                int delta = INF;
                int j1 = 0;
                for (int j = 1; j <= n; ++j)
                    if (!used[j]) {
                        int cur = a[i0][j] - u[i0] - v[j];
                        if (cur < minv[j]) {
                            minv[j] = cur;
                            w[j] = j0;
                        }
                        if (minv[j] < delta) {
                            delta = minv[j];
                            j1 = j;
                        }
                    }
                for (int j = 0; j <= n; ++j) {
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minv[j] -= delta;
                    }
                }
                j0 = j1;
            } while (p[j0] != 0);
            do {
                int j1 = w[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }

        answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            answer[p[i]] = i;
        }

        cost = -v[0];
    }
    
    public void printAnswer() {
        out.printf("%d\n", cost);
        for (int i = 1; i <= n; i++) {
            out.printf("%d %d\n", i, answer[i]);
        }        
    }

    public void run() {
        try {
            in = new FastScanner(new File("assignment.in"));
            out = new PrintWriter(new File("assignment.out"));

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
        new A().run();
    }
}