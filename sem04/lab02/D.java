import java.util.*;
import java.io.*;

public class D {
    static FastScanner in;
    static PrintWriter out;

    public void solve() throws IOException {
        String s = in.next();
        State[] states = buildSuffixAutomaton(s);

        int[][] graph = new int[states.length][];
        int size = 0;
        for (int i = 0; i < states.length; i++) {
            graph[i] = states[i].children();
            size += graph[i].length;
        }
        out.println(states.length + " " + size);

        for (int i = 0; i < states.length; i++) {
            states[i].childrenPrint(i);
        }

        int terminal = 0;
        for (int i = 0; i < states.length; i++) {
            if (states[i].length == s.length()) {
                terminal = i;
            }
        }

        int current = terminal;
        int totalTerminals = 0;
        while (current != -1) {
            totalTerminals++;
            current = states[current].link;
        }
        out.println(totalTerminals);
        current = terminal;
        while (current != -1) {
            out.print(current + 1);
            out.print(" ");
            current = states[current].link;
        }
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

        public int[] children() {
            int[] children = new int[128];
            int cur = 0;
            for (int i : next) {
                if (i != -1) {
                    children[cur++] = i;
                }
            }
            return Arrays.copyOf(children, cur);
        }
        public void childrenPrint(int parent) {
            for (int i = 0; i < 128; i++) {
                if (next[i] != -1) {
                    out.print(parent + 1);
                    out.print(" ");
                    out.print(next[i] + 1);
                    out.print(" ");
                    out.print((char)(i));
                    out.println();
                }
            }
        }
    }

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

    public void run() {
        try {
            in = new FastScanner(new File("automaton.in"));
            out = new PrintWriter(new File("automaton.out"));

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