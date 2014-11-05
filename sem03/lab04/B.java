import java.util.*;
import java.io.*;

public class B {
    FastScanner in;
    PrintWriter out;

    class Edge implements Comparable<Edge> {
        int from;
        int to;
        int distance;

        Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(distance, o.distance);
        }
    }

    ArrayList<Edge> edges;
    int[] p;
    int[] h;
    int n;
    int m;

    int getDSU(int v) {
        if (v == p[v]) {
            return v;
        } else {
            p[v] = getDSU(p[v]);
            return p[v];
        }
    }

    void uniteDSU(int x, int y) {
        x = getDSU(x);
        y = getDSU(y);
        if (x != y) {
            p[x] = y;
        }
    }

    void initiateDSU() {
        for (int i = 0; i < n; i++) {
            p[i] = i;
            h[i] = 0;
        }
    }

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        p = new int[n];
        h = new int[n];
        edges = new ArrayList<Edge>();

        for (int i = 0; i < m; i++) {
            edges.add(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }

        out.println(getWeight());
    }

    public int getWeight() {
        int answer = Integer.MAX_VALUE;
        Collections.sort(edges);

        for (Edge edge : edges) {
            if (getDSU(edge.from) != getDSU(edge.to)) {
                answer += edge.distance;
                uniteDSU(edge.from, edge.to);
            }
        }

        return answer;
    }

    public void run() {
        try {
            in = new FastScanner(new File("spantree2.in"));
            out = new PrintWriter(new File("spantree2.out"));

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