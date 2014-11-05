import java.util.*;
import java.io.*;

public class G {
    FastScanner in;
    PrintWriter out;

    ArrayList<Integer>[] graph;

    public void solve() throws IOException {
        int numberOfTests = in.nextInt();
        for (int i = 0; i < numberOfTests; i++) {
            String input = in.next();
            int n = numberOfDeps(input);
            if (n < 5) {
                out.println("YES");
                continue;
            }
            if (n == 5) {
                if (input.contains("0")) {
                    out.println("YES");
                    continue;
                } else {
                    out.println("NO");
                    continue;
                }
            }

            //check k5


            graph = new ArrayList[n+1];

            for (int j = 0; j < n; j++) {
                graph[j] = new ArrayList<>();
            }
            int edges = 0;

            int rowLength = 1;
            int curY = 2;
            int curX = 1;
            for (char a : input.toCharArray()) {
                if (a == '1') {
                    edges++;
                    graph[curX - 1].add(curY - 1);
                    graph[curY - 1].add(curX - 1);
                }
                curX++;
                if (curX > rowLength) {
                    rowLength++;
                    curX = 1;
                    curY++;
                }
            }

            if (checkFive(edges) || checkThreeThree()) {
                out.println("NO");
            } else {
                out.println("YES");
            }
        }
    }
    public int numberOfDeps(String s) {
        switch (s.length()) {
            case 1:
                return 2;
            case 3:
                return 3;
            case 6:
                return 4;
            case 10:
                return 5;
            default:
                return 6;
        }
    }

    boolean checkThreeThree() {
        boolean flag = false;
        for (int i = 0; i < 6 && !flag; i++) {
            for (int j = 0; j < 6 && !flag; j++) {
                if (j == i)
                    continue;
                for (int k = 0; k < 6; k++) {
                    if (k == i || k == j)
                        continue;
                    int count = 0;
                    for (int l = 0; l < 6; l++) {
                        if (l != i && l != j && l != k && graph[i].contains(l) && graph[j].contains(l) && graph[k].contains(l))
                            count ++;
                    }
                    if (count == 3) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    boolean checkFive(int edges) {
        for (int i = 0; i < 6; i++) {
            if (graph[i].size() <= 1 && edges - graph[i].size() == 10)
                return true;
            int count = 0;
            boolean[] visited = new boolean[6];
            if (graph[i].size() >= 2) {
                for (int neigh : graph[i]) {
                    for (int to : graph[neigh])
                        if (graph[i].contains(to) && !visited[to] && to != i) {
                            count++;
                        }
                    visited[neigh] = true;
                }
            }
            if (graph[i].size() - count == 1 && edges - graph[i].size() + 1 == 10 && graph[i].size() > 2 || count == 0 && edges - graph[i].size() + 1 == 10 && graph[i].size() == 2 || edges - graph[i].size() == 10)
                return true;
        }
        return false;
    }

    public void run() {
        try {
            in = new FastScanner(new File("planaritycheck.in"));
            out = new PrintWriter(new File("planaritycheck.out"));

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
        new G().run();
    }
}