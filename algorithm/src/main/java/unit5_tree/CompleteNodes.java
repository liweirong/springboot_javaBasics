package unit5_tree;

/**
 * LeetCode 222 完全二叉树的结点个数
 */
public class CompleteNodes {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = binaryTree.buildBinaryTree();

        System.out.println(countNodes(binaryTree.root));

    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

}
