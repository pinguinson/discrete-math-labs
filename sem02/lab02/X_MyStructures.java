class MyPriorityQueue {

    int[] queue = new int[10];
    int freeSpace = 1;

    MyPriorityQueue() {
        queue = new int[10];
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

    void siftDown(int i) {
        if (numberOfChildren(i) == 0) {
            return;
        } else if (numberOfChildren(i) == 1) {
            if (leftChild(i) < getValue(i)) {
                swapElements(i, 2*i);
                siftDown(2*i);
            } else {
                return;
            }
        } else if (numberOfChildren(i) == 2) {
            if (getValue(i) > Math.min(leftChild(i), rightChild(i))) {
                if (leftChild(i) < rightChild(i)) {
                    swapElements(i, 2 * i);
                    siftDown(2 * i);
                } else {
                    swapElements(i, 2 * i + 1);
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
        if (getParent(i) < getValue(i)) {
            swapElements(parentIndex(i), i);
            siftUp(parentIndex(i));
        } else {
            return;
        }
    }

    void push(int value) {
        queue[freeSpace] = value;
        siftUp(freeSpace);
        freeSpace++;
    }

    String extractMin() {
        if (freeSpace == 1) {
            return "*";
        } else {
            String s = Integer.toString(queue[1]);
            queue[1] = Integer.MAX_VALUE;
            siftDown(1);
            freeSpace--;
            return s;
        }
    }

}