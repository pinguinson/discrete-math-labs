import java.io.*;

public class A_SimpleBST {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String s;
        BinarySearchTree bst = new BinarySearchTree();
        while ((s = in.next()) != null) {
            if (s.substring(0,3).equals("ins")) {
                bst.insert(Integer.parseInt(s.substring(7)));
            } else if (s.substring(0,3).equals("del")) {
                bst.delete(Integer.parseInt(s.substring(7)));
            } else if (s.substring(0,3).equals("nex")) {
                out.println(bst.next(Integer.parseInt(s.substring(5))));
            } else if (s.substring(0,3).equals("pre")) {
                out.println(bst.prev(Integer.parseInt(s.substring(5))));
            } else {
                out.println(bst.exists(Integer.parseInt(s.substring(7))));
            }
        }
    }

    class Node {

        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int x){
            this.value = x;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

    }

    class BinarySearchTree {
        Node root;

        public BinarySearchTree() {
            this.root = null;
        }

        public void insertNode(Node startNode, Node adding) {
            if (root == null) {
                this.root = adding;
            } else if (adding.value > startNode.value) {
                if (startNode.right != null) {
                    insertNode(startNode.right, adding);
                } else {
                    adding.parent = startNode;
                    startNode.right = adding;
                }
            } else {
                if (startNode.left != null) {
                    insertNode(startNode.left, adding);
                } else {
                    adding.parent = startNode;
                    startNode.left = adding;
                }
            }
        }

        public void insert(int value) {
            if (!exists(value)) {
                insertNode(root, new Node(value));
            }
        }

        public void deleteNode(Node target) {

            //target has no children
            if (target.left == null && target.right == null) {
                if (target == root) {
                    root = null;
                }
                if (target.parent != null) {
                    if (target.parent.left != null && target.parent.left == target) {
                        target.parent.left = null;
                    } else {
                        target.parent.right = null;
                    }
                }
                target = null;
                return;
            }

            //target has only right child
            if (target.left == null) {
                if (target.parent != null && target.parent.left == target) {
                    target.parent.left = target.right;
                    target.right.parent = target.parent;
                } else if (target.parent != null && target.parent.right == target) {
                    target.parent.right = target.right;
                    target.right.parent = target.parent;
                }
                target = null;
                return;
            }
            //target has only left child
            if (target.right == null) {
                if (target.parent != null && target.parent.left == target) {
                    target.parent.left = target.left;
                    target.left.parent = target.parent;
                } else if (target.parent != null && target.parent.right == target) {
                    target.parent.right = target.left;
                    target.left.parent = target.parent;
                }
                target = null;
                return;
            }
            //target has two children
            Node nextToTarget = nextNode(target);
            deleteNode(nextNode(target));
            target.value = nextToTarget.value;
            return;
        }

        public void delete(int value) {
            if (exists(value)) {
                deleteNode(search(root, value));
            }
        }

        public boolean exists(int x) {
            return (search(root, x) != null);
        }

        public Node search(Node startNode, int x) {
            if (startNode == null || startNode.value == x) {
                return startNode;
            } else if (x < startNode.value) {
                return search(startNode.left, x);
            } else {
                return search(startNode.right, x);
            }
        }

        public Node getMin(Node startNode) {
            if (startNode == null) {
                return null;
            } else if (startNode.left == null) {
                return startNode;
            } else {
                return getMin(startNode.left);
            }
        }

        public Node getMax(Node startNode) {
            if (startNode == null) {
                return null;
            } else if (startNode.right == null) {
                return startNode;
            } else {
                return getMax(startNode.right);
            }
        }

        public Node nextNode(Node startNode) {
            if (startNode == null) {
                return null;
            } else if (root != null && getMax(root).value == startNode.value) {
                return null;
            } else if (startNode.right != null) {
                return getMin(startNode.right);
            }
            Node y = startNode.parent;
            while (y != null && startNode == y.right) {
                startNode = y;
                y = y.parent;
            }
            return y;
        }

        public Node prevNode(Node startNode) {
            if (startNode == null) {
                return null;
            } else if (root != null && getMin(root).value == startNode.value) {
                return null;
            } else if (startNode.left != null) {
                return getMin(startNode.left);
            }
            Node y = startNode.parent;
            while (y != null && startNode == y.left) {
                startNode = y;
                y = y.parent;
            }
            return y;
        }

        public String next(int value) {
            if (getMax(root) == null) {
                return "none";
            } else if (value >= getMax(root).value) {
                return "none";
            } else {
                if (!exists(value)) {
                    insert(value);
                    String buff = Integer.toString(nextNode(search(root, value)).value);
                    delete(value);
                    return buff;
                } else {
                    return Integer.toString(nextNode(search(root, value)).value);
                }
            }
        }

        public String prev(int value) {
            if (getMin(root) == null) {
                return "none";
            } else if (value <= getMin(root).value) {
                return "none";
            } else {
                if (!exists(value)) {
                    insert(value);
                    String buff = Integer.toString(prevNode(search(root, value)).value);
                    delete(value);
                    return buff;
                } else {
                    return Integer.toString(prevNode(search(root, value)).value);
                }
            }
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("bstsimple.in"));
            out = new PrintWriter(new File("bstsimple.out"));

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
        new A_SimpleBST().run();
    }
}