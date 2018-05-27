package faces.awesome.utils.navigator;

import java.util.Map;

/**
 * @author Linus Wallman
 *
 * When creating new nodes, use the constructor with the following pattern: new Node(VALUEOFNODE, LEFTNODE, RIGHTNODE, UPNODE, DOWNNODE)
 */
public class TreeNavigator<T> {

    private Node<T> root;

    public TreeNavigator(int value) {
        root = createLeaf(value);
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> node) {
        root = node;
    }

    public Node<T> createLeaf(int value) {
        return new Node<>(value);
    }

    public void addDownNode(int value, Node<T> node) {
        node.down = createLeaf(value);
        node.down.up = node;
    }

    public void addLeftNode(int value, Node<T> node) {
        node.left = createLeaf(value);
        node.left.right = node;
    }

    public void addRightNode(int value, Node<T> node) {
        node.right = createLeaf(value);
        node.right.left = node;
    }

    public void addUpNode(int value, Node<T> node) {
        node.up = createLeaf(value);
        node.up.down = node;
    }

    public class Node<T> {
        private int value;
        private Node<T> left;
        private Node<T> right;
        private Node<T> up;
        private Node<T> down;

        private Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public Node getUp() {
            return up;
        }

        public Node getDown() {
            return down;
        }
    }
}
