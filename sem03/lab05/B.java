import java.util.*;
import java.io.*;

public class B {
    FastScanner in;
    PrintWriter out;

    public static final int INF = Integer.MAX_VALUE;

    boolean[] used;
    int[][] capacity;
    ArrayList<Edge>[] graph;
    int n;
    int m;

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        used = new boolean[n];
        Arrays.fill(used, false);
        capacity = new int[n][n];
        graph = new ArrayList[m];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int edgeCapacity = in.nextInt();
            capacity[u][v] = edgeCapacity;
            capacity[v][u] = edgeCapacity;
            graph[u].add(new Edge(u, v, graph[v].size(), edgeCapacity));
            graph[v].add(new Edge(v, u, graph[u].size() - 1, 0));
        }

        out.println(maxFlow(0, n - 1));
    }

    static class Edge {
        int from, to, reverse, capacity, flow;

        public Edge(int from, int to, int reverse, int capacity) {
            this.from = from;
            this.to = to;
            this.reverse = reverse;
            this.capacity = capacity;
        }
    }

    int maxFlow(int s, int t) {
        int flow = 0;
        int[] q = new int[m];
        while (true) {
            int qt = 0;
            q[qt++] = s;
            Edge[] predecessor = new Edge[graph.length];
            for (int qh = 0; qh < qt && predecessor[t] == null; qh++) {
                int cur = q[qh];
                for (Edge edge : graph[cur]) {
                    if (predecessor[edge.to] == null && edge.capacity > edge.flow) {
                        predecessor[edge.to] = edge;
                        q[qt++] = edge.to;
                    }
                }
            }
            if (predecessor[t] == null) {
                break;
            }
            int deltaFlow = Integer.MAX_VALUE;
            for (int u = t; u != s; u = predecessor[u].from) {
                deltaFlow = Math.min(deltaFlow, predecessor[u].capacity - predecessor[u].flow);
            }
            for (int u = t; u != s; u = predecessor[u].from) {
                predecessor[u].flow += deltaFlow;
                graph[predecessor[u].to].get(predecessor[u].reverse).flow -= deltaFlow;
            }
            flow += deltaFlow;
        }
        return flow;
    }

    int findPath(int from, int t, int f) {
        if (from == t) {
            return f;
        }
        used[from] = true;
        for (int to = 0; to < used.length; to++)
            if (!used[to] && capacity[from][to] > 0) {
                int deltaFlow = findPath(to, t, Math.min(f, capacity[from][to]));
                if (deltaFlow > 0) {
                    capacity[from][to] -= deltaFlow;
                    capacity[to][from] += deltaFlow;
                    return deltaFlow;
                }
            }
        return 0;
    }

    public void run() {
        try {
            in = new FastScanner(new File("maxflow.in"));
            out = new PrintWriter(new File("maxflow.out"));

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