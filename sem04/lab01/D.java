import java.util.*;
import java.io.*;

public class D {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String s = in.next();
        int[] ans = zFunction(s);
        for (int i = 1; i < ans.length; i++) {
            out.printf("%d ", ans[i]);
        }
    }

    public static int[] zFunction(String s) {
        int[] zf = new int[s.length()];
        int left = 0;
        int right = 0;
        for (int i = 1; i < s.length(); i++) {
            zf[i] = Math.max(0, Math.min((right - i), zf[i - left]));
            while ((i + zf[i] < s.length()) && (s.charAt(zf[i]) == s.charAt(i + zf[i]))) {
                zf[i]++;
            }
            if (i + zf[i] >= right) {
                left = i;
                right = i + zf[i];
            }
        }
        return zf;
    }

    public void run() {
        try {
            in = new FastScanner(new File("z.in"));
            out = new PrintWriter(new File("z.out"));

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
        new D().run();
    }
}