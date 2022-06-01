// CharTreeNode objects stores a single node of a binary tree of ints.

public class CharTreeNode {
    public char data; // data stored at this node
    public CharTreeNode left; // reference to left subtree
    public CharTreeNode right; // reference to right subtree

    // Constructs a leaf node with the given data.
    public CharTreeNode(char data) {
        this(data, null, null);
    }

    // Constructs a leaf or branch node with the given data and links.
    public CharTreeNode(char data, CharTreeNode left, CharTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}