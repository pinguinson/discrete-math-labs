import java.util.*;
import java.io.*;

public class E {
    FastScanner in;
    PrintWriter out;

    public static final long INF = Long.MAX_VALUE / 2;

    long[] d;
    boolean[] usedBFS;
    boolean[] usedDFS;
    int n;
    ArrayList<Edge> edges;
    ArrayList<Pair>[] graph;

    public void solve() throws IOException {
        n = in.nextInt();
        int m = in.nextInt();
        int start = in.nextInt() - 1;

        d = new long[n];
        usedBFS = new boolean[n];
        usedDFS = new boolean[n];
        graph = new ArrayList[n];
        Arrays.fill(d, INF);
        edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            long distance = in.nextLong();
            edges.add(new Edge(from, to, distance));
            graph[from].add(new Pair(to, distance));
        }

        bfs(start);
        fordBellman(start);

        for (int i = 0; i < m; i++) {
            int from = edges.get(i).from;
            int to = edges.get(i).to;
            long cost = edges.get(i).cost;
            if (d[to] > d[from] + cost) {
                if (usedBFS[to] && !usedDFS[from]) {
                    dfs(to);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            if (d[j] == INF) {
                out.println("*");
            } else if (usedDFS[j]) {
                out.println("-");
            } else if (-INF < d[j] && d[j] < INF) {
                out.println(d[j]);
            }
        }
    }

    public void fordBellman(int start) {
        d[start] = 0;
        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                if (d[edge.from] < INF) {
                    if (d[edge.from] + edge.cost < d[edge.to]) {
                        d[edge.to] = Math.max(-INF, d[edge.from] + edge.cost);
                    }
                }
            }
        }
    }

    void dfs(int start) {
        usedDFS[start] = true;
        for (Pair edge : graph[start]) {
            if (!usedDFS[edge.to])
                dfs(edge.to);
        }
    }

    public void bfs(int start) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int v = queue.pollFirst();
            for (Pair edge : graph[v]) {
                if (!usedBFS[edge.to]) {
                    usedBFS[edge.to] = true;
                    queue.add(edge.to);
                }
            }
        }
    }

    class Edge {
        public int from;
        public int to;
        public long cost;

        public Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    class Pair implements Comparable<Pair> {
        int to;
        long cost;

        public Pair (int a, long b) {
            to = a;
            cost = b;
        }

        @Override
        public int compareTo(Pair o) {
            if (o.to != this.to) {
                return Integer.compare(o.to, this.to);
            } else {
                return Long.compare(o.cost, this.cost);
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("path.in"));
            out = new PrintWriter(new File("path.out"));

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
        new E().run();
    }
}