import java.io.*;
import java.util.*;

public class H_Quack {
    FastScanner in;
    PrintWriter out;

    String[] commands = new String[100000];
    MyQueueOnMyLinkedList q = new MyQueueOnMyLinkedList();

    int[] registers = new int[26];
    int[] jumpMap;
    int totalCommands;
    int pointer;

    public void solve() throws IOException {
        totalCommands = in.getInput();
        jumpMap = new int[totalCommands];
        Arrays.fill(jumpMap, -1);

        pointer = 0;
        while (pointer < totalCommands) {
            getCommand(pointer);
        }

    }

    public void getSum() {
        int x = q.pop();
        int y = q.pop();
        int ans = (x + y) & 0x0000ffff;
        q.push(ans);
    }

    public void getDif() {
        int x = q.pop();
        int y = q.pop();
        int ans = (x - y) & 0x0000ffff;
        q.push(ans);
    }

    public void getPro() {
        int x = q.pop();
        int y = q.pop();
        int ans = (x * y) & 0x0000ffff;
        q.push(ans);
    }

    public void getDiv() {
        int x = q.pop();
        int y = q.pop();
        if (x == 0 && y == 0){
            q.push(0);
            return;
        }
        int ans = (x / y) & 0x0000ffff;
        q.push(ans);
    }

    public void getMod() {
        int x = q.pop();
        int y = q.pop();
        if (x == 0 && y == 0){
            q.push(0);
            return;
        }
        int ans = (x % y) & 0x0000ffff;
        q.push(ans);
    }

    public void setReg(char reg) {
        registers[reg - 'a'] = q.pop();
    }

    public void getReg(char reg) {
        q.push(registers[reg - 'a']);
    }

    public void printInt() {
        out.println(q.pop());
    }

    public void printInt(char reg) {
        out.println(registers[reg - 'a']);
    }

    public void printChar() {
        int c = q.pop() % 256;
        if (c == 10) {
            out.println();
        } else {
            out.print(Character.toChars(c));
        }
    }

    public void printChar(char reg) {
        int c = registers[reg - 'a'] % 256;
        if (c == 10) {
            out.println();
        } else {
            out.print(Character.toChars(c));
        }
    }

    public void jump(int n) {
        if (jumpMap[n] > 0) {
            pointer = jumpMap[n];
        } else {
            String mark = commands[n].substring(1);
            for (int i = 0; i < totalCommands; i++) {
                if (commands[i].charAt(0) == ':' && commands[i].substring(1).equals(mark)){
                    jumpMap[n] = i;
                    pointer = i;
                    return;
                }
            }
        }
    }

    public void jump(char reg, int n) {
        if (registers[reg - 'a'] == 0) {
            if (jumpMap[n] > 0) {
                pointer = jumpMap[n];
            } else {
                String mark = commands[n].substring(2);
                for (int i = 0; i < totalCommands; i++) {
                    if (commands[i].charAt(0) == ':' && commands[i].substring(1).equals(mark)){
                       jumpMap[n] = i;
                       pointer = i;
                       return;
                    }
                }
            }
        } else {
            pointer++;
        }
    }

    public void jumpEquals(char reg1, char reg2, int n) {
        if (registers[reg1 - 'a'] == registers[reg2 - 'a']) {
            if (jumpMap[n] > 0) {
                pointer = jumpMap[n];
            } else {
                String mark = commands[n].substring(3);
                for (int i = 0; i < totalCommands; i++) {
                    if (commands[i].charAt(0) == ':' && commands[i].substring(1).equals(mark)){
                        jumpMap[n] = i;
                        pointer = i;
                        return;
                    }
                }
            }
        } else {
            pointer++;
        }
    }

    public void jumpGreater(char reg1, char reg2, int n) {
        if (registers[reg1 - 'a'] > registers[reg2 - 'a']) {
            if (jumpMap[n] > 0) {
                pointer = jumpMap[n];
            } else {
                String mark = commands[n].substring(3);
                for (int i = 0; i < totalCommands; i++) {
                    if (commands[i].charAt(0) == ':' && commands[i].substring(1).equals(mark)){
                        jumpMap[n] = i;
                        pointer = i;
                        return;
                    }
                }
            }
        } else {
            pointer++;
        }
    }

    public void pushInt(int value) {
        q.push(value % 65536);
    }

    public void getCommand(int i) {
        String s = commands[i];
        char first = s.charAt(0);
        switch (first) {
            case '%':   getMod();
                        pointer++;
                        break;
            case '*':   getPro();
                        pointer++;
                        break;
            case '/':   getDiv();
                        pointer++;
                        break;
            case '+':   getSum();
                        pointer++;
                        break;
            case '-':   getDif();
                        pointer++;
                        break;
            case ':':   pointer++;
                        break;
            case '>':   setReg(s.charAt(1));
                        pointer++;
                        break;
            case '<':   getReg(s.charAt(1));
                        pointer++;
                        break;
            case 'P':   if (s.length() == 1){
                            printInt();
                        } else {
                            printInt(s.charAt(1));
                        }
                        pointer++;
                        break;
            case 'C':   if (s.length() > 1){
                            printChar(s.charAt(1));
                        } else {
                            printChar();
                        }
                        pointer++;
                        break;
            case 'J':   jump(pointer);
                        break;
            case 'Z':   jump(s.charAt(1), pointer);
                        break;
            case 'E':   jumpEquals(s.charAt(1), s.charAt(2), pointer);
                        break;
            case 'G':   jumpGreater(s.charAt(1), s.charAt(2), pointer);
                        break;
            case 'Q':   pointer = totalCommands;
                        break;
            case '0':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
            case '1':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
            case '2':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
            case '3':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
            case '4':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
            case '5':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
            case '6':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
            case '7':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
            case '8':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
            case '9':   pushInt(Integer.parseInt(s));
                        pointer++;
                        break;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("quack.in"));
            out = new PrintWriter(new File("quack.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyNode {

        public int value;
        MyNode next;

        public MyNode(int x){
            value = x;
            next = null;
        }
    }

    class MyLinkedList {

        MyNode head;
        private int size;

        public MyLinkedList() {
            head = null;
            size = 0;
        }

        public void insertHead(int x) {
            MyNode n = new MyNode(x);
            n.next = head;
            head = n;
            size++;
        }

        public void insertHead(MyNode newHead) {
            newHead.next = head;
            head = newHead;
            size++;
        }

        public int removeHead() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            MyNode prev = head;
            int ans = prev.value;
            head = prev.next;
            prev = null;
            size--;
            return ans;
        }

        public void insertAfter(MyNode after, MyNode newNode) {
            newNode.next = after.next;
            after.next = newNode;
            size++;
        }

        public int removeAfter(MyNode node) {
            if (node.next == null) {
                throw new IndexOutOfBoundsException();
            }
            MyNode obsolete = node.next;
            int ans = obsolete.value;
            node.next = obsolete.next;
            obsolete = null;
            size--;
            return ans;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    class MyQueueOnMyLinkedList {

        MyLinkedList list = new MyLinkedList();
        MyNode tail = new MyNode(0);

        public MyQueueOnMyLinkedList(){
            list = new MyLinkedList();
        }

        public void push(int value) {
            MyNode newNode = new MyNode(value);
            if (isEmpty()) {
                list.insertHead(newNode);
            } else {
                list.insertAfter(tail, newNode);
            }
            tail = newNode;
        }

        public int pop() {
            return (list.removeHead());
        }

        public boolean isEmpty() {
            return (list.isEmpty());
        }

        public int size() {
            return (list.size());
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

        int getInput() throws IOException {
            int i = 0;
            String thisLine;
            while ((thisLine = br.readLine()) != null) {
                commands[i] = thisLine;
                i++;
            }
            return i;
        }
    }

    public static void main(String[] arg) {
        new H_Quack().run();
    }
}
