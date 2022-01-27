package homework.task4_tree;


import java.util.List;

/**
 * 树和递归讲义 2.3的【3】LeetCode 199 二叉树的右视图 我们分析了右视图的问题，
 * 那如果是俯视图或者左视图会怎么样呢？在【5】我们也来造题简要做了分析，
 * 现在请你修改199题，实现左视图的功能
 *
 */

public class 题目2_左视图 {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = binaryTree.buildBinaryTree();
        List<Integer> res = null;
        //todo 这里实现你的方法
        res = leftSideView(binaryTree.root);
        System.out.println(res);

    }

    /**
     * todo 这里实现你的方法
     * @param root
     * @return
     */
    public static List<Integer> leftSideView(TreeNode root) {

        return null;
    }

    public static TreeNode buildBinaryTree() {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.right = new TreeNode(7);
        node.right.left = new TreeNode(15);
        return node;

    }
}
