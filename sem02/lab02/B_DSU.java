import java.io.*;

public class B_DSU {
    FastScanner in;
    PrintWriter out;

    int[] parents = new int[100001];
    int[] size = new int[100001];
    int[] max = new int[100001];
    int[] min = new int[100001];

    public void solve() throws IOException {
        /* MAIN CODE GOES HERE!!! */
        int n = Integer.parseInt(in.next());
        for (int i = 1; i <= n; i++) {
            makeSet(i);
        }
        String s;
        while ((s = in.next()) != null) {
            if (s.charAt(0) == 'u') {
                String[] thisLine;
                thisLine = s.split("\\s+");
                unionSets(Integer.parseInt(thisLine[1]), Integer.parseInt(thisLine[2]));
            } else if (s.charAt(0) == 'g') {
                String[] thisLine;
                thisLine = s.split("\\s+");
                int x = Integer.parseInt(thisLine[1]);
                out.printf("%d %d %d\n", getMin(x), getMax(x), getSize(x));
            }
        }
    }

    //Creates a new set with only one element x
    void makeSet(int x) {
        parents[x] = x;
        size[x] = 1;
        min[x] = x;
        max[x] = x;
    }

    //Merges two sets containing elements x and y
    void unionSets(int x, int y) {
        x = findSet(x);
        y = findSet(y);
        if (parents[x] != parents[y]) {
            if (getSize(x) < getSize(y)) {
                swap(x, y);
            }
            size[x] += size[y];
            min[x] = Math.min(getMin(x), getMin(y));
            max[x] = Math.max(getMax(x), getMax(y));
            parents[y] = x;
        }
    }

    //Returns leader of the set containing element x
    int findSet(int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = findSet(parents[x]);
        }
    }

    void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    int getMin(int x) {
        return min[findSet(x)];
    }

    int getMax(int x) {
        return max[findSet(x)];
    }

    int getSize(int x) {
        return size[findSet(x)];
    }


    public void run() {
        try {
            in = new FastScanner(new File("dsu.in"));
            out = new PrintWriter(new File("dsu.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() throws IOException {
            return br.readLine();
        }
    }

    public static void main(String[] arg) {
        new B_DSU().run();
    }
}