import java.util.*;
import java.io.*;

public class D {
    FastScanner in;
    PrintWriter out;

    ArrayList<Integer>[] graph;
    int[] color;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();

        graph = new ArrayList[n+1];
        color = new int[n+1];
        Arrays.fill(color, 0);

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            graph[from].add(to);
        }

        if (dfs(s)) {
            out.println("First player wins");
        } else {
            out.println("Second player wins");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("game.in"));
            out = new PrintWriter(new File("game.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean dfs(int v) {
        boolean result = false;
        color[v] = 1;
        if (graph[v] == null) {
            return result;
        }
        for (int i = 0; i < graph[v].size(); i++) {
            int to = graph[v].get(i);
            if (!dfs(to)) {
                result = true;
            }
        }
        return result;
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