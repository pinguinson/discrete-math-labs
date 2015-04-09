import java.util.*;
import java.io.*;

public class D {
    FastScanner in;
    PrintWriter out;

    boolean[] used;
    ArrayList<Integer>[] edges;
    int[] next;
    int[] prev;
    int n;
    int m;
    int k;

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
        }

        next = new int[n];
        prev = new int[n];
        used = new boolean[n];

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            edges[u].add(v);
        }

        algoFordFulkerson();

        int total = 0;
        for (int i = 0; i < n; i++) {
            if (next[i] != -1) {
                total++;
            }
        }
        out.println(n - total);

    }



    public void algoFordFulkerson() {
        Arrays.fill(next, -1);
        Arrays.fill(prev, -1);
        boolean isPath = true;
        while (isPath) {
            isPath = false;
            Arrays.fill(used, false);
            for (int from = 0; from < n; from++) {
                if (next[from] == -1) {
                    if (dfs(from)) {
                        isPath = true;
                    }
                }
            }
        }
    }

    public boolean dfs(int from) {
        if (used[from]) {
            return false;
        }
        used[from] = true;
        for (int to : edges[from]) {
            if (prev[to] == -1) {
                prev[to] = from;
                next[from] = to;
                return true;
            } else {
                if (dfs(prev[to])) {
                    prev[to] = from;
                    next[from] = to;
                    return true;
                }
            }
        }
        return false;
    }

    public void run() {
        try {
            in = new FastScanner(new File("paths.in"));
            out = new PrintWriter(new File("paths.out"));

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