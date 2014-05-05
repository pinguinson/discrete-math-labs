import java.util.*;
import java.io.*;

public class D_Parking {
    FastScanner in;
    PrintWriter out;

    int n;
    int m;
    boolean parking[];
    int parents[];

    public void solve() throws IOException {
        n = in.nextInt();
        parking = new boolean[n];
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            parking[i] = true;
        }

        for (int i = 0; i < n; i++) {
            int cur = in.nextInt();
            out.println(enter(cur-1)+1);
        }
    }

    int findSet(int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = findSet(parents[x]);
        }
    }

    int enter(int x) {
        int ans = findSet(x);
        parking[ans] = false;
        parents[ans] = findSet((ans + 1) % n);
        return ans;
    }




    public void run() {
        try {
            in = new FastScanner(new File("parking.in"));
            out = new PrintWriter(new File("parking.out"));

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
        new D_Parking().run();
    }
}