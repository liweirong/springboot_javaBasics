package unit5_tree;

/**
 * leetcode104 对称问题
 */
public class Symmetry {
    public static void main(String[] args) {
        BinaryTree bTree = new BinaryTree();

        bTree.root = bTree.buildBinaryTree3();
//        bTree.root = bTree.buildBinaryTreeComplex();

        boolean result;
        //LeetCode101 对称二叉树
        result = isSymmetric(bTree.root);
        System.out.println(result);
    }

    /**
     * LeetCode101 对称二叉树
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return check(root.left, root.right);
    }

    public static boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

}
