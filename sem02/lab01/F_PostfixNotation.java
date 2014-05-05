import java.util.*;
import java.io.*;

public class F_PostfixNotation {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        MyStackOnMyVector st = new MyStackOnMyVector(10);
        try{
            while(true) {
                /* MAIN CODE GOES HERE!!! */
                String s = in.next();
                if (operator(s) < 0) {
                    st.push(Integer.parseInt(s));
                } else {
                    switch (operator(s)) {
                        case 1: st.push(st.pop() + st.pop());
                                break;
                        case 2: st.push(-(st.pop() - st.pop()));
                                break;
                        case 3: st.push(st.pop() * st.pop());
                                break;
                    }
                }
            }
        } catch (NullPointerException exc){}
        out.println(st.pop());
    }

    public int operator(String s) {
        if (s.charAt(0) == '+'){
            return 1;
        }
        if (s.charAt(0) == '-'){
            return 2;
        }
        if (s.charAt(0) == '*'){
            return 3;
        }
        return -1;
    }

    public void run() {
        try {
            in = new FastScanner(new File("postfix.in"));
            out = new PrintWriter(new File("postfix.out"));

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
        new F_PostfixNotation().run();
    }
}