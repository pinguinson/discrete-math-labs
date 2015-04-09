import java.util.*;
import java.io.*;

public class C {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String s = in.next();
        int[] ans = buildPrefix(s);
        for (int i = 0; i < ans.length; i++) {
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

    public static int[] buildPrefix(int[] z) {
        int[] p = new int[z.length];
        for (int i = 1; i < z.length; i++) {
            for (int j = z[i] - 1; j >= 0; j--) {
                if (p[i + j] > 0) {
                    break;
                } else {
                    p[i + j] = j + 1;
                }
            }
        }
        return p;
    }

    public static int[] buildPrefix(String s) {
        return buildPrefix(zFunction(s));
    }

    public void run() {
        try {
            in = new FastScanner(new File("prefix.in"));
            out = new PrintWriter(new File("prefix.out"));

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
        new C().run();
    }
}