import java.util.*;
import java.io.*;

public class E {
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

        if (hasCycle) {
            out.print("YES");
        } else {
            out.print("NO");
        }

    }

    public boolean hasCycle(int v) {
        color[v] = 1;
        for (int i = 0; i < graph[v].size(); i++) {
            int to = graph[v].get(i);
            if (color[to] == 0) {
                hasCycle(to);
            }
            if (color[to] == 1) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        try {
            in = new FastScanner(new File(".in"));
            out = new PrintWriter(new File(".out"));

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
        new E().run();
    }
}