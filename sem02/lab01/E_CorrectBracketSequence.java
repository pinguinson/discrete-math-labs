import java.util.*;
import java.io.*;

public class E_CorrectBracketSequence {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        /* MAIN CODE GOES HERE!!! */
        try{
            while(true) {
                String s = in.next();
                MyStackOnMyVector stack = new MyStackOnMyVector(10);
                boolean fl = true;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '('){
                        stack.push(1);
                    }
                    if (s.charAt(i) == ')'){
                        if (stack.isEmpty()){
                            out.println("NO");
                            fl = false;
                            break;
                        }
                        int a = stack.pop();
                        if (a != 1) {
                            stack.push(a);
                            stack.push(-1);
                        }
                    }
                    if (s.charAt(i) == '['){
                        stack.push(2);
                    }
                    if (s.charAt(i) == ']'){
                        if (stack.isEmpty()){
                            out.println("NO");
                            fl = false;
                            break;
                        }
                        int a = stack.pop();
                        if (a != 2) {
                            stack.push(a);
                            stack.push(-2);
                        }
                    }

                }
                if (stack.isEmpty() && fl){
                    out.println("YES");
                } else if (fl){
                    out.println("NO");
                }
            }
        } catch (NullPointerException ignored){
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("brackets.in"));
            out = new PrintWriter(new File("brackets.out"));

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

        public boolean isEmpty() {
            return st.size() == 0;
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
        new E_CorrectBracketSequence().run();
    }
}