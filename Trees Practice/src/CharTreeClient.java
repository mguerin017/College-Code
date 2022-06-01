// Helene Martin, CSE 143
// Creates a tree of characters and prints its elements in different orders.

public class CharTreeClient {
    public static void main(String[] args) {
        // build a tree out of nodes (use the jGRASP debugger to see its
        // structure)
        CharTreeNode root = new CharTreeNode('M');
        root.left = new CharTreeNode('A');
        root.left.left = new CharTreeNode('R');
        root.left.left.left = new CharTreeNode('K');
        root.left.left.right = new CharTreeNode('G');
        root.left.right = new CharTreeNode('U');
        root.right = new CharTreeNode('E');
        root.right.left = new CharTreeNode('I');
        root.right.right = new CharTreeNode('N');


        // set the tree's overall root as the root of the tree we just built
        CharTree tree = new CharTree(root);

        // use the jGRASP debugger to see the different traversal orders
        tree.printPreOrder();
        tree.printInOrder();
        tree.printPostOrder();
    }
}
