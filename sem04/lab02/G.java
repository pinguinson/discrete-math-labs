import java.util.*;
import java.io.*;

public class G {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String s1 = in.next();
        String s2 = in.next();
        out.println(lcs(s1, s2));
    }

    public static class State {
        int length;
        int link;
        int[] next = new int[128];
        int end;
        List<Integer> ilink = new ArrayList<>(0);

        public State() {
            Arrays.fill(next, -1);
        }
    }

    static int bestState;

    public static State[] buildSuffixAutomaton(String s) {
        int n = s.length();
        State[] st = new State[Math.max(2, 2 * n - 1)];
        st[0] = new State();
        st[0].link = -1;
        st[0].end = -1;
        int last = 0;
        int size = 1;
        for (char c : s.toCharArray()) {
            int cur = size++;
            st[cur] = new State();
            st[cur].length = st[last].length + 1;
            st[cur].end = st[last].length;
            int p;
            for (p = last; p != -1 && st[p].next[c] == -1; p = st[p].link) {
                st[p].next[c] = cur;
            }
            if (p == -1) {
                st[cur].link = 0;
            } else {
                int q = st[p].next[c];
                if (st[p].length + 1 == st[q].length)
                    st[cur].link = q;
                else {
                    int clone = size++;
                    st[clone] = new State();
                    st[clone].length = st[p].length + 1;
                    st[clone].next = st[q].next.clone();
                    st[clone].link = st[q].link;
                    for (; p != -1 && st[p].next[c] == q; p = st[p].link)
                        st[p].next[c] = clone;
                    st[q].link = clone;
                    st[cur].link = clone;
                    st[clone].end = -1;
                }
            }
            last = cur;
        }
        for (int i = 1; i < size; i++) {
            st[st[i].link].ilink.add(i);
        }
        return Arrays.copyOf(st, size);
    }

    static String lcs(String a, String b) {
        State[] st = buildSuffixAutomaton(a);
        bestState = 0;
        int len = 0;
        int bestLen = 0;
        int bestPos = -1;
        for (int i = 0, cur = 0; i < b.length(); ++i) {
            char c = b.charAt(i);
            if (st[cur].next[c] == -1) {
                for (; cur != -1 && st[cur].next[c] == -1; cur = st[cur].link) {
                }
                if (cur == -1) {
                    cur = 0;
                    len = 0;
                    continue;
                }
                len = st[cur].length;
            }
            ++len;
            cur = st[cur].next[c];
            if (bestLen < len) {
                bestLen = len;
                bestPos = i;
                bestState = cur;
            }
        }
        return b.substring(bestPos - bestLen + 1, bestPos + 1);
    }

    public void run() {
        try {
            in = new FastScanner(new File("common.in"));
            out = new PrintWriter(new File("common.out"));

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