import java.util.*;
import java.io.*;

public class A_StackOnVector {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        /* MAIN CODE GOES HERE!!! */
        MyStackOnMyVector stack = new MyStackOnMyVector(10);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (s.charAt(0) == '-') {
                out.println(stack.pop());
            } else if (s.charAt(0) == '+') {
                stack.push(in.nextInt());
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("stack1.in"));
            out = new PrintWriter(new File("stack1.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyVector {

        private int[] vec;
        private int pointer;

        public MyVector(int size) {
            pointer = 0;
            vec = new int[size];
        }

        public int get(int index) {
            if (index >= vec.length)
                throw new ArrayIndexOutOfBoundsException(index);
            return vec[index];
        }

        public void set(int index, int value) {
            if (index >= vec.length)
                throw new ArrayIndexOutOfBoundsException(index);
            vec[index] = value;
        }

        public void add(int value) {
            if (pointer == vec.length) {
                int[] newVector = new int[vec.length*2];
                System.arraycopy(vec, 0, newVector, 0, vec.length);
                vec = newVector;
            }
            vec[pointer++] = value;
        }

        public void del() {
            if (pointer == 0) {
                throw new ArrayIndexOutOfBoundsException(pointer);
            } else {
                pointer--;
                if (pointer * 4 < vec.length && vec.length > 10) {
                    int[] newVector = new int[vec.length/2];
                    System.arraycopy(vec, 0, newVector, 0, pointer + 1);
                    vec = null;
                    vec = newVector;
                }
            }
        }

        public int size() {
            return pointer;
        }
    }

    class MyStackOnMyVector {

        MyVector st = new MyVector(10);

        public MyStackOnMyVector(int size) {
            st = new MyVector(size);
        }

        public void push(int value) {
            st.add(value);
        }

        public int pop() {
            int ans = st.get(st.size()-1);
            st.del();
            return ans;
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
        new A_StackOnVector().run();
    }
}