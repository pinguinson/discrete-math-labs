import java.util.*;
import java.io.*;

public class C_QueueOnVector {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        /* MAIN CODE GOES HERE!!! */
        MyQueueOnMyVector stack = new MyQueueOnMyVector();
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
            in = new FastScanner(new File("queue1.in"));
            out = new PrintWriter(new File("queue1.out"));

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

        public int length() {
            return vec.length;
        }

        public int size() {
            return pointer;
        }
    }

    class MyQueueOnMyVector {

        MyVector q = new MyVector(10);
        int size = 0;
        int head = 0;
        int tail = -1;

        public MyQueueOnMyVector() {
            q = new MyVector(10);
        }

        public void push(int value) {
            q.add(value);
            tail++;
            size++;
        }

        public int pop() {
            int ans = q.get(head);
            head++;
            size--;
            /*
            if (size == q.length() / 4) {
                MyVector temp = new MyVector(q.length() / 2);
                for (int i = head; i <= tail; i++){
                    temp.add(q.get(i));
                }
                q = temp;
                temp = null;
            }
            */
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
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
        new C_QueueOnVector().run();
    }
}