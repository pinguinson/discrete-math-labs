import java.util.*;
import java.io.*;

public class B {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String sub = in.next();
        String main = in.next();
        ArrayList<Integer> starts = substringSearch(main, sub);
        out.println(starts.size());
        for (Integer i : starts) {
            out.printf("%d ", i);
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
        System.err.println(Arrays.toString(zf));
        return zf;
    }

    ArrayList<Integer> substringSearch(String source, String needle) {
        ArrayList<Integer> ans = new ArrayList<>();
        int[] zf = zFunction(needle + '#' + source);
        for (int i = needle.length() + 1; i < source.length() + needle.length(); i++) {
            if (zf[i] == needle.length()) {
                ans.add(i - needle.length());
            }
        }
        return ans;
    }

    public void run() {
        try {
            in = new FastScanner(new File("search2.in"));
            out = new PrintWriter(new File("search2.out"));

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
        new B().run();
    }
}