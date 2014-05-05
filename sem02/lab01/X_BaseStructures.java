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

class MyStackOnMyVector {

    MyVector st = new MyVector(10);

    public MyStackOnMyVector(int size) {
        st = new MyVector(size);
    }

    public void push(int value) {
        st.add(value);
    }

    public int pop() {
        int ans = st.get(st.size() - 1);
        st.del();
        return ans;
    }

    public boolean isEmpty() {
        return st.size() == 0;
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

class MyStackOnMyLinkedList {

    MyLinkedList st = new MyLinkedList();

    public MyStackOnMyLinkedList() {
        st = new MyLinkedList();
    }

    public void push(int value) {
        st.insertHead(value);
    }

    public int pop() {
        return st.removeHead();
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return st.isEmpty();
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
        if (size < q.length() / 4) {
            MyVector temp = new MyVector(q.length() / 2);
            for (int i = head; i <= tail; i++){
                temp.add(q.get(i));
            }
            q = temp;
            temp = null;
        }
        return ans;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
