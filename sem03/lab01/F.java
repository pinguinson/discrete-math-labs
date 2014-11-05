import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by pinguinson on 02.10.2014.
 */
public class F {
    FastScanner in;
    PrintWriter out;

    public static final int MAXN = 100000;

    ArrayList<Integer>[] graph = new ArrayList[MAXN];
    int [] color = new int[MAXN];
    ArrayList<Integer> answer = new ArrayList<Integer>();
    boolean hasCycle = false;



    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
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

        topSort();
        int cur = 0;
        while (cur + 1 < n && graph[answer.get(cur)].contains(answer.get(cur + 1))) {
            cur++;
        }
        if (cur == n - 1) {
            out.print("YES");
        } else {
            out.print("NO");
        }
    }

    public void dfs(int v) {
        if (color[v] != 2) {
            color[v] = 1;
            if (graph[v] == null) {
                color[v] = 2;
                answer.add(v);
                return;
            }
            for (int i = 0; i < graph[v].size(); i++) {
                int to = graph[v].get(i);
                if (color[to] == 0) {
                    dfs(to);
                }
                if (color[to] == 1) {
                    hasCycle = true;
                    return;
                }
            }
            color[v] = 2;
            answer.add(v);
        }
    }

    public void topSort() {
        for (int i = 1; i < graph.length; i++) {
            if (color[i] == 0) {
                dfs(i);
            }
        }
        Collections.reverse(answer);
    }

    public void run() {
        try {
            in = new FastScanner(new File("hamiltonian.in"));
            out = new PrintWriter(new File("hamiltonian.out"));

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
