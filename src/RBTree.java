//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RBTree {
    private Node root;
    RBTree tree;

    public RBTree() {
    }

    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        y.parent = y;
    }

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }

        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }

        y.right = x;
        y.left = y;
    }

    public void insert(int key, int value) {
        Node newNode = new Node();
        newNode.parent = null;
        newNode.key = key;
        newNode.value = value;
        newNode.left = null;
        newNode.right = null;
        newNode.color = Node.Color.RED;
        if (this.root == null) {
            this.root = newNode;
            this.root.color = Node.Color.BLACK;
        } else {
            Node currentNode = this.root;

            while(true) {
                while(newNode.key >= currentNode.key) {
                    if (currentNode.right == null) {
                        currentNode.right = newNode;
                    } else {
                        currentNode = currentNode.right;
                    }
                }

                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    return;
                }

                currentNode = currentNode.left;
            }
        }
    }

    @BeforeEach
    void initTest() {
        this.tree = new RBTree();
    }

    @Test
    void shouldCreateTree() {
        assert this.tree != null;

    }

    @Test
    void shouldInsert() {
        this.tree.insert(1, 1);

        assert this.root.key == 1;

        this.tree.insert(3, 3);

        assert this.root.right.key == 3;

        this.tree.insert(2, 2);

        assert this.root.right.left.key == 2;

    }

    void transplat( Node u, Node v){
        if(u.parent == null) {
            this.root = v;
        } else if( u == u.parent.left){
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public int get(int key) {
        return this.root.value;
    }

    @Test
    void shouldReturnValue(){
        this.tree.insert(1, 45);
        assert  this.tree.get(1) == 45;
    }

    int remove(int key) {
        if(this.root.left == null ){
            Node z = this.root;
            Node x = this.root.right;
            transplat(z, x);
            deleteFixup(x);
        } else if (this.root.right == null) {
            Node z = this.root;
            Node x = this.root.right;
            transplat(z, x);
            deleteFixup(x);
        } else {
            Node z = this.root;
            Node checkedRoot;
            do{
                checkedRoot = this.root.left;
            }while(checkedRoot.left != null);
            transplat(z, checkedRoot);
            deleteFixup(z);
        }
        return this.root.value;

    }

    @Test
    void shouldReturnValue0(){
        this.tree.insert(1, 45);
        assert  this.tree.remove(1) == 0;
    }

    void deleteFixup(Node x) {
        while(x != this.root && x.color == Node.Color.BLACK){
            if( x == x.parent.left){
                Node w = x.parent.right;
                if (w.color == Node.Color.RED){
                    w.color = Node.Color.BLACK;
                    x.parent.color = Node.Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Node.Color.BLACK && w.right.color == Node.Color.BLACK){
                    w.color = Node.Color.RED;
                    x = x.parent;
                }else{
                    if (w.right.color == Node.Color.BLACK){
                        w.left.color = Node.Color.BLACK;
                        w.color = Node.Color.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Node.Color.BLACK;
                    w.right.color = Node.Color.BLACK;
                    leftRotate(x.parent);
                    x = this.root;
                }
            } else {
            Node w = x.parent.left;
            if (w.color == Node.Color.RED) {
                w.color = Node.Color.BLACK;
                x.parent.color = Node.Color.RED;
                rightRotate(x.parent);
                w = x.parent.left;
            }
            if (w.right.color == Node.Color.BLACK && w.left.color == Node.Color.BLACK) {
                w.color = Node.Color.RED;
                x = x.parent;
            } else {
                if (w.left.color == Node.Color.BLACK) {
                    w.right.color = Node.Color.BLACK;
                    w.color = Node.Color.RED;
                    leftRotate(w);
                    w = x.parent.left;
                }
                w.color = x.parent.color;
                x.parent.color = Node.Color.BLACK;
                w.left.color = Node.Color.BLACK;
                rightRotate(x.parent);
                x = this.root;
            }
        }
    }
    x.color = Node.Color.BLACK;
}
    
    int height(Node node) {
    if (node == null) {
        return 0;
    } else {
        return 1 + Math.max(height(node.left), height(node.right));
    }
}

@Test
void testHeight() {
    // Create a new Red-Black Tree
    RBTree tree = new RBTree();

    // Add some nodes to the tree
    // The exact method to add nodes will depend on your implementation
    tree.insert(10);
    tree.insert(20);
    tree.insert(30);
    tree.insert(40);
    tree.insert(50);

    // Check that the height of the tree is as expected
    // The expected height will depend on the specific nodes you added
    assertEquals(3, tree.height(tree.root));
}

    @Test
    void shouldGet1() {
        assert 1 == this.get(1);

    }
}