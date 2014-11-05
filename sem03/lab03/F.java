import java.util.*;
import java.io.*;

public class F {
    FastScanner in;
    PrintWriter out;

    public static final long INF = 2_000_000_000;

    long[] d;
    int[] p;
    int n;
    ArrayList<Edge> edges;

    public void solve() throws IOException {
        n = in.nextInt();

        d = new long[n];
        p = new int[n];
        Arrays.fill(d, INF);
        Arrays.fill(p, -1);
        edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int distance = in.nextInt();
                if (distance != 1_000_000_000) {
                    edges.add(new Edge(i, j, distance));
                }
            }
        }

        fordBellman(0);
    }

    public void fordBellman(int start) {
        d[start] = 0;
        int x = -1;
        for (int i = 0; i < n; i++) {
            x = -1;
            for (Edge edge : edges) {
                if (d[edge.b] > d[edge.a] + edge.cost) {
                    d[edge.b] = Math.max(-INF, d[edge.a] + edge.cost);
                    p[edge.b] = edge.a;
                    x = edge.b;
                }
            }
        }

        if (x == -1) {
            out.println("NO");
        } else {
            int y = x;
            for (int i = 0; i < n; i++) {
                y = p[y];
            }
            ArrayList<Integer> path = new ArrayList<>();
            for (int cur = y; ; cur = p[cur]) {
                path.add(cur);
                if (cur == y && path.size() > 1) {
                    break;
                }
            }

            Collections.reverse(path);
            out.println("YES");
            out.println(path.size());
            for (int a : path) {
                out.print((a + 1) + " ");
            }
            out.println();
        }
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
            in = new FastScanner(new File("negcycle.in"));
            out = new PrintWriter(new File("negcycle.out"));

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
        new F().run();
    }
}