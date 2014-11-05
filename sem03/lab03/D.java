import java.util.*;
import java.io.*;

public class D {
    FastScanner in;
    PrintWriter out;

    public static final int INF = Integer.MAX_VALUE;

    ArrayList<Edge> graph[];
    int[] d;
    int[] p;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        graph = new ArrayList[n];
        d = new int[n];
        p = new int[n];
        Arrays.fill(d, INF);
        Arrays.fill(p, -1);

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int weight = in.nextInt();
            graph[a].add(new Edge(b, weight));
            graph[b].add(new Edge(a, weight));
        }

        dijkstra(0);
    }

    public void dijkstra(int start) {
        d[start] = 0;
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add((long) start);
        while (!q.isEmpty()) {
            long cur = q.remove();
            int v = (int) cur;
            if (cur >>> 32 != d[v]) {
                continue;
            }
            for (Edge edge : graph[v]) {
                int to = edge.to;
                if (d[to] > d[v] + edge.cost) {
                    d[to] = d[v] + edge.cost;
                    p[to] = v;
                    q.add(((long) (d[v] + edge.cost) << 32) + to);
                }
            }
        }

        for (int a : d) {
            out.print(a + " ");
        }
    }

    class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("pathbgep.in"));
            out = new PrintWriter(new File("pathbgep.out"));

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
        new D().run();
    }
}