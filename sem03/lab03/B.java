import java.util.*;
import java.io.*;

public class B {
    FastScanner in;
    PrintWriter out;

    public static final long INF = 2_000_000_000;

    long[] d;
    int n;
    ArrayList<Edge> edges;

    public void solve() throws IOException {
        n = in.nextInt();
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;

        d = new long[n];
        Arrays.fill(d, INF);
        edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int distance = in.nextInt();
                if (distance >= 0) {
                    edges.add(new Edge(i, j, distance));
                }
            }
        }

        out.print(fordBellman(start, finish));
    }

    public long fordBellman(int start, int finish) {
        d[start] = 0;
        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                d[edge.b] = Math.min(d[edge.b], d[edge.a] + edge.cost);
            }
        }
        long result = d[finish];
        if (result != INF && checkFordBellman()) {
            return result;
        } else {
            return -1;
        }
    }

    public boolean checkFordBellman() {
        for (Edge edge : edges) {
            if (d[edge.b] > d[edge.a] + edge.cost)
                return false;
        }
        return true;
    }

    class Edge {
        public int a;
        public int b;
        public int cost;

        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("pathmgep.in"));
            out = new PrintWriter(new File("pathmgep.out"));

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
        new B().run();
    }
}