import java.util.*;
import java.io.*;

public class F {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String s = in.next();
        smth(s);
    }

    public static int[] suffixArray(CharSequence S) {
        int n = S.length();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++)
            order[i] = n - 1 - i;

        Arrays.sort(order, (a, b) -> Character.compare(S.charAt(a), S.charAt(b)));

        int[] sa = new int[n];
        int[] classes = new int[n];
        for (int i = 0; i < n; i++) {
            sa[i] = order[i];
            classes[i] = S.charAt(i);
        }

        for (int len = 1; len < n; len *= 2) {
            int[] c = classes.clone();
            for (int i = 0; i < n; i++) {
                classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && sa[i - 1] + len < n && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
            }
            int[] cnt = new int[n];
            for (int i = 0; i < n; i++)
                cnt[i] = i;
            int[] s = sa.clone();
            for (int i = 0; i < n; i++) {
                int s1 = s[i] - len;
                if (s1 >= 0)
                    sa[cnt[classes[s1]]++] = s1;
            }
        }
        return sa;
    }

    public static class Triple {
        int w;
        int id;
        int h;

        public Triple(int w, int id, int h) {
            this.w = w;
            this.id = id;
            this.h = h;
        }
    }

    public static String smth(String s) {
        Stack<Triple> stack = new Stack<>();
        int maxH = s.length();
        int maxW = 1;
        int start = 0;
        int[] sa = suffixArray(s);
        int[] lcp = lcp(sa, s);
        System.err.println(lcp.length);
        for (int i = 0; i < s.length() - 1; i++) {
            int x = 1;
            while(!stack.isEmpty() && lcp[i] <= stack.peek().h) {
                Triple tri = stack.pop();
                x += tri.w;
                if (x * tri.h > maxW * maxH) {
                    maxW = x;
                    maxH = tri.h;
                    start = sa[tri.id];
                }
            }
            if (stack.isEmpty() || lcp[i] > stack.peek().h) {
                stack.push(new Triple(x, i , lcp[i]));
            }
        }
        return s.substring(start, start + maxH);
    }

    public static int[] lcp(int[] sa, CharSequence s) {
        int n = sa.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++)
            rank[sa[i]] = i;
        int[] lcp = new int[n];
        for (int i = 0, h = 0; i < n; i++) {
            if (rank[i] < n - 1) {
                for (int j = sa[rank[i] + 1]; Math.max(i, j) + h < s.length() && s.charAt(i + h) == s.charAt(j + h); ++h)
                    ;
                lcp[rank[i]] = h;
                if (h > 0)
                    --h;
            }
        }
        return lcp;
    }

    public static long countSubstrings(String s) {
        int[] lcp = lcp(suffixArray(s), s);
        long n = s.length();
        long sum = 0;
        for (int i : lcp) {
            sum += i;
        }
        return (n * (n + 1) / 2 - sum);
    }

    public void run() {
        try {
            in = new FastScanner(new File("count.in"));
            out = new PrintWriter(new File("count.out"));

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
        new F().run();
    }
}