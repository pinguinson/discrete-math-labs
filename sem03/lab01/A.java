import java.util.*;
import java.io.*;

public class A {
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

        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            if (graph[from] == null) {
                graph[from] = new ArrayList<Integer>();
            }
            graph[from].add(to);
        }

        topSort();
        if (hasCycle) {
            out.print(-1);
            return;
        }

        for (Integer anAnswer : answer) {
            out.printf("%d ", anAnswer);
        }
    }

    public void dfs(int v) {
        if (hasCycle) {
            return;
        }
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
            in = new FastScanner(new File("topsort.in"));
            out = new PrintWriter(new File("topsort.out"));

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
        new A().run();
    }
}