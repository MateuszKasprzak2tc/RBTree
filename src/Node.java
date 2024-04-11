//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class Node {
    int key;
    int value;
    Node left;
    Node right;
    Node parent;
    Color color;

    Node() {
    }

    static enum Color {
        RED,
        BLACK;

        private Color() {
        }
    }
}
