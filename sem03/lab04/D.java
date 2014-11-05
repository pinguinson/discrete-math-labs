import java.util.*;
import java.io.*;

public class D {
    FastScanner in;
    PrintWriter out;

    class Edge {
        int u;
        int v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    int n;
    ArrayList<Edge> edges;
    int[][] graph;
    int[] power;
    int totalOdd;
    ArrayList<Integer> eulerPath;

    public void solve() throws IOException {
        n = in.nextInt();
        edges = new ArrayList<Edge>();
        graph = new int[n][n];
        power = new int[n];
        totalOdd = 0;
        eulerPath = new ArrayList<Integer>();

        for (int from = 0; from < n; from++) {
            int total = in.nextInt();
            for (int j = 0; j < total; j++) {
                int to = in.nextInt() - 1;
                graph[from][to]++;
                power[to]++;
                power[from]++;
            }
        }

        for (int i = 0; i < n; i++) {
            totalOdd += power[i] % 2;
        }

        if (totalOdd > 2) {
            out.println(-1);
            return;
        }

        findEulerPath(0);

        out.println(eulerPath.size() - 1);
        for (int a : eulerPath) {
            out.print(a + " ");
        }
    }

    public void findEulerPath(int from) {
        for (int to = 0; to < n; to++) {
            if (graph[from][to] > 0) {
                graph[from][to]--;
                graph[to][from]--;
                findEulerPath(to);
            }
        }
        eulerPath.add(from + 1);
    }

    public void run() {
        try {
            in = new FastScanner(new File("euler.in"));
            out = new PrintWriter(new File("euler.out"));

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