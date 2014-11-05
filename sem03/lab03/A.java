import java.util.*;
import java.io.*;

public class A {
    FastScanner in;
    PrintWriter out;

    ArrayList<Integer>[] graph;
    int[] d;
    boolean[] used;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        graph = new ArrayList[n+1];
        used = new boolean[n+1];
        d = new int[n+1];

        Arrays.fill(used, false);

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        bfs(1);

        for (int i = 1; i <= n; i++) {
            out.print(d[i]);
            out.print(" ");
        }
    }

    public void bfs(int start) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        d[start] = 0;
        used[start] = true;
        while (!queue.isEmpty()) {
            int v = queue.pollFirst();
            for (int to : graph[v]) {
                if (!used[to]) {
                    used[to] = true;
                    queue.add(to);
                    d[to] = d[v] + 1;
                }
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("pathbge1.in"));
            out = new PrintWriter(new File("pathbge1.out"));

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