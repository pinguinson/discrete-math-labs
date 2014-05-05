import java.util.*;
import java.io.*;

public class A_IsHeap {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        /* MAIN CODE GOES HERE!!! */
        int n = in.nextInt();
        int[] a = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        boolean ans = true;
        for (int i = 1; i <= n; i++) {
            if ((2*i <= n) && (a[i] > a[2*i])) {
                ans = false;
                break;
            }
            if ((2*i + 1 <= n) && (a[i] > a[2*i + 1])) {
                ans = false;
                break;
            }
        }

        if (ans) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("isheap.in"));
            out = new PrintWriter(new File("isheap.out"));

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
        new A_IsHeap().run();
    }
}