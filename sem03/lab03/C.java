import java.util.*;
import java.io.*;

public class C {
    FastScanner in;
    PrintWriter out;

    public static final long INF = 2_000_000_000;

    long[][] d;
    int n;

    public void solve() throws IOException {
        n = in.nextInt();
        int m = in.nextInt();

        d = new long[n][n];
        for (long[] row: d)
            Arrays.fill(row, INF);

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int distance = in.nextInt();
            d[from][to] = distance;
        }

        for (int i = 0; i < n; i++) {
            d[i][i] = 0;
        }

        floydWarshall();
        printDistances();

    }

    public void floydWarshall() {
        for (int intermediate = 0; intermediate < n; intermediate++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    if (d[from][intermediate] < INF && d[intermediate][to] < INF) {
                        d[from][to] = Math.min(d[from][to], d[from][intermediate] + d[intermediate][to]);
                    }
                }
            }
        }
    }

    public void printDistances() {
        for (int from = 0; from < n; from++) {
            for (int to = 0; to < n; to++) {
                out.print(d[from][to] + " ");
            }
            out.println();
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("pathsg.in"));
            out = new PrintWriter(new File("pathsg.out"));

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