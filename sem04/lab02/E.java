import java.util.*;
import java.io.*;

public class E {
    static FastScanner in;
    static PrintWriter out;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        char[] str = new char[n];
        for (int i = 0; i < n; i++) {
            str[i] = (char) (in.nextInt() + 64);
        }
        String s = new String(str);
        findRefrain(s);
    }

    public static int[] suffixArray(String string) {
        int n = string.length();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++)
            order[i] = n - 1 - i;

        Arrays.sort(order, (a, b) -> Character.compare(string.charAt(a), string.charAt(b)));

        int[] suffixArray = new int[n];
        int[] classes = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = order[i];
            classes[i] = string.charAt(i);
        }

        for (int len = 1; len < n; len *= 2) {
            int[] c = classes.clone();
            for (int i = 0; i < n; i++) {
                classes[suffixArray[i]] = i > 0 && c[suffixArray[i - 1]] == c[suffixArray[i]] && suffixArray[i - 1] + len < n && c[suffixArray[i - 1] + len / 2] == c[suffixArray[i] + len / 2] ? classes[suffixArray[i - 1]] : i;
            }
            int[] cnt = new int[n];
            for (int i = 0; i < n; i++)
                cnt[i] = i;
            int[] s = suffixArray.clone();
            for (int i = 0; i < n; i++) {
                int s1 = s[i] - len;
                if (s1 >= 0)
                    suffixArray[cnt[classes[s1]]++] = s1;
            }
        }
        return suffixArray;
    }

    public static void findRefrain(String s) {
        int[] suffixArray = suffixArray(s + '$');
        int[] lcp = lcp(suffixArray, s);
        int[] stack = new int[lcp.length];
        int length = s.length();
        int top = 0;
        int bestPosition = -1;
        int bestLength = -1;
        for (int i = 0; i < lcp.length; ++i) {
            while (top > 0 && lcp[stack[top - 1]] >= lcp[i]) {
                int current = stack[top - 1];
                int previous = top == 1 ? -1 : stack[top - 2];
                int currentLength = i - previous;
                if (bestPosition == -1 ||
                        (long) lcp[bestPosition] * bestLength < (long) lcp[current] * currentLength) {
                    bestPosition = current;
                    bestLength = currentLength;
                }
                top--;
            }
            stack[top++] = i;
        }
        if ((long) lcp[bestPosition] * bestLength < length) {
            out.println(length);
            out.println(length);
            for (int i = 0; i < length; ++i) {
                if (i > 0) out.print(' ');
                out.print(s.charAt(i) - 64);
            }
        } else {
            out.println((long) lcp[bestPosition] * bestLength);
            out.println(lcp[bestPosition]);
            for (int i = 0; i < lcp[bestPosition]; ++i) {
                if (i > 0) out.print(' ');
                out.print(s.charAt(suffixArray[bestPosition] + i) - 64);
            }
        }
    }

    public static int[] lcp(int[] sa, String s) {
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

    public void run() {
        try {
            in = new FastScanner(new File("refrain.in"));
            out = new PrintWriter(new File("refrain.out"));

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