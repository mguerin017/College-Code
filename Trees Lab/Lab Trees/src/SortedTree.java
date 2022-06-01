public class SortedTree{
    TreeNode root;
    public SortedTree(int[] input){
        root = buildTree(input, 0, input.length-1); // this calls your method
    }

    /**
     * Builds a binary using the elements from input[start]
     * to input[end]. So start and end are indices for the array input
     */
    private TreeNode buildTree(int[] input, int start, int end) {
        /* Return null when the indices cross, preventing duplicates/infinite loops */
        if (start > end) {
            return null;
        }
        /* Find the middle index of the array/subarray being input */
        int middleIndex = ((start + end) / 2);
        /* Create a new node with the value from the middle of the array/subarray */
        TreeNode nd = new TreeNode(input[middleIndex]);
        /* Create a new subtree to the left using the lower half of the array */
        nd.left = buildTree(input, start, middleIndex - 1);
        /* Create a new subtree to the right using the upper half of the array */
        nd.right = buildTree(input, middleIndex + 1, end);
        /* Return the root of the entire tree */
        return nd;
    }

    private void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.println(root.info + " ");
            printInOrder(root.right);
        }
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }


    public static void main(String args[]){
        int[] input = {4, 9, 15, 20, 22, 24, 35, 87};

        //1 - create the binary search tree given the sorted input
        SortedTree st = new SortedTree(input);

        //2 - print the tree node values inorder, see earlier practice etc.
        st.printInOrder();
    }
}