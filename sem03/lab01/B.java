import java.util.*;
import java.io.*;

public class B {
    FastScanner in;
    PrintWriter out;

    ArrayList<Integer>[] g, h;
    boolean[] used;
    int[] result;
    ArrayList<Integer> vertexOrder;
    ArrayList<Integer> component;


    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int totalComponents = 0;

        g = new ArrayList[n+1];
        h = new ArrayList[n+1];
        used = new boolean[n+1];
        result = new int[n+1];
        Arrays.fill(used, false);
        vertexOrder = new ArrayList<Integer>();
        component = new ArrayList<Integer>();


        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<Integer>();
            h[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i <= m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();

            g[from].add(to);
            h[to].add(from);
        }

        for(int i = 1; i <= n; i++) {
            if (!used[i]) {
                dfs(i);
            }
        }

        Arrays.fill(used, false);

        for(int i = 0; i < n; i++) {
            int v = vertexOrder.get(n - i - 1);
            if (!used[v]) {
                dfsReversed(v);
                for (int j = 0; j < component.size(); j++) {
                    int cur = component.get(j);
                    result[cur] = totalComponents + 1;
                }
                component.clear();
                totalComponents++;
            }
        }
        out.println(totalComponents);

        for (int i = 1; i <= n; i++) {
            out.printf("%d ", result[i]);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("cond.in"));
            out = new PrintWriter(new File("cond.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dfs(int v) {
        used[v] = true;
        if (g[v] == null) {
            vertexOrder.add(v);
            return;
        }
        for (int i = 0; i < g[v].size(); i++) {
            int to = g[v].get(i);
            if (!used[to]) {
                dfs(to);
            }
        }
        vertexOrder.add(v);
    }

    public void dfsReversed(int v) {
        used[v] = true;
        component.add(v);
        if (h[v] == null) {
            return;
        }
        for (int i = 0; i < h[v].size(); i++) {
            int to = h[v].get(i);
            if (!used[to]) {
                dfsReversed(to);
            }
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