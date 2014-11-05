import java.util.*;
import java.io.*;

public class C {
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

    public static final int INF = 1_000_000_000;

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
        edges = new ArrayList<Edge>();
        p = new int[n];
        h = new int[n];

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int distance = in.nextInt();
            edges.add(new Edge(from, to, distance));
        }

        int result = getWeight();
        if (result >= 0) {
            out.println("YES");
            out.println(result);
        } else {
            out.println("NO");
        }
    }

    public int getWeight() {
        Collections.sort(edges);
        boolean existsMST = false;
        int answer = 2 * INF + 42;

        for (int shortestEdge = 0; shortestEdge < m; shortestEdge++) {
            initiateDSU();
            int longestEdge = -INF;
            int usedEdges = 0;
            for (int i = shortestEdge; i < m; i++) {
                Edge edge = edges.get(i);
                int from = getDSU(edge.from);
                int to = getDSU(edge.to);
                if (from != to) {
                    uniteDSU(from, to);
                    longestEdge = edge.distance;
                    usedEdges++;
                }
            }
            if (usedEdges == n - 1) {
                existsMST = true;
            } else {
                continue;
            }
            int difference = longestEdge - edges.get(shortestEdge).distance;
            answer = Math.min(answer, difference);
        }

        if (!existsMST) {
            return -1;
        } else {
            return answer;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("mindiff.in"));
            out = new PrintWriter(new File("mindiff.out"));

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