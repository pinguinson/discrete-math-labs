import java.util.*;
import java.io.*;

public class A {
    FastScanner in;
    PrintWriter out;

    ArrayList<Integer>[] graph;
    boolean[] used;
    int[] comp;
    int counter = 0;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        graph = new ArrayList[n+1];
        used = new boolean[n+1];
        comp = new int[n+1];
        Arrays.fill(used, false);

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i <= m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        findComponents();

        out.println(counter);
        for (int i = 1; i < comp.length; i++) {
            int aComp = comp[i];
            out.printf("%d ", aComp);
        }
    }

    public void dfs(int v) {
        used[v] = true;
        comp[v] = counter;
        for (int to : graph[v]) {
            if (!used[to]) {
                dfs(to);
            }
        }
    }

    public void findComponents() {
        for (int i = 1; i < graph.length; i++) {
            if (!used[i]) {
                counter++;
                dfs(i);
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("components.in"));
            out = new PrintWriter(new File("components.out"));

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