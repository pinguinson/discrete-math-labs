import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class C_PriorityQueue {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        /* MAIN CODE GOES HERE!!! */
        MyPriorityQueue q = new MyPriorityQueue();
        String s;
        int i = 0;
        while ((s = in.next()) != null) {
            i++;
            if (s.substring(0, 4).equals("push")) {
                 String[] command = s.split("\\s+");
                 q.push(Integer.parseInt(command[1]), i);
            }
            if (s.substring(0, 4).equals("extr")) {
                out.println(q.extractMin());
            }
            if (s.substring(0, 4).equals("decr")) {
                String[] command = s.split("\\s+");
                q.decreaseKey(Integer.parseInt(command[2]), Integer.parseInt(command[1]));
            }
        }
    }

    class MyPriorityQueue {
        int[] queue = new int[16];
        int[] index = new int[16];
        int freeSpace = 1;

        MyPriorityQueue() {
            queue = new int[16];
            index = new int[16];
            freeSpace = 1;
        }

        boolean hasParent(int i) {
            return i > 0;
        }

        int parentIndex(int i) {
            return i / 2;
        }

        int getParent(int i) {
            return getValue(parentIndex(i));
        }

        int getValue(int i) {
            return queue[i];
        }

        int leftChild(int i) {
            return (queue[2 * i]);
        }

        int rightChild(int i) {
            return (queue[2 * i + 1]);
        }

        int numberOfChildren(int i) {
            if ((2 * i + 1) < freeSpace) {
                return 2;
            } else if ((2 * i) < freeSpace) {
                return 1;
            } else {
                return 0;
            }
        }

        void swapElements(int a, int b) {
            int temp = queue[a];
            queue[a] = queue[b];
            queue[b] = temp;
        }

        void swapIndexes(int a, int b) {
            int temp = index[a];
            index[a] = index[b];
            index[b] = temp;
        }

        void swapAll(int a, int b) {
            swapElements(a, b);
            swapIndexes(a, b);
        }


        void siftDown(int i) {
            if (numberOfChildren(i) == 0) {
                return;
            } else if (numberOfChildren(i) == 1) {
                if (leftChild(i) < getValue(i)) {
                    swapAll(i, 2*i);
                    siftDown(2*i);
                } else {
                    return;
                }
            } else if (numberOfChildren(i) == 2) {
                if (getValue(i) > Math.min(leftChild(i), rightChild(i))) {
                    if (leftChild(i) < rightChild(i)) {
                        swapAll(i, 2 * i);
                        siftDown(2 * i);
                    } else {
                        swapAll(i, 2 * i + 1);
                        siftDown(2 * i + 1);
                    }
                } else {
                    return;
                }
            }
        }

        void siftUp(int i) {
            if (i == 1) {
                return;
            }
            if (getParent(i) > getValue(i)) {
                swapAll(parentIndex(i), i);
                siftUp(parentIndex(i));
            } else {
                return;
            }
        }

        void push(int value, int i) {
            if (freeSpace == queue.length) {
                int[] tempQueue = new int[queue.length * 2];
                System.arraycopy(queue, 0, tempQueue, 0, queue.length);
                queue = tempQueue;
                tempQueue = new int[queue.length * 2];
                System.arraycopy(index, 0, tempQueue, 0, index.length);
                index = tempQueue;
            }
            queue[freeSpace] = value;
            index[freeSpace] = i;
            siftUp(freeSpace);
            freeSpace++;
        }

        void decreaseKey(int value, int k) {
            for (int i = 0; i < index.length; i++) {
                if (index[i] == k) {
                    queue[i] = value;
                    siftUp(i);
                    return;
                }
            }
        }

        String extractMin() {
            if (freeSpace == 1) {
                return "*";
            } else {
                String s = Integer.toString(queue[1]);
                freeSpace--;
                queue[1] = queue[freeSpace];
                index[1] = index[freeSpace];
                siftDown(1);
                return s;
            }
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("priorityqueue.in"));
            out = new PrintWriter(new File("priorityqueue.out"));

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
        new C_PriorityQueue().run();
    }
}