package unit5_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode104 最大深度和最小深度问题
 */
public class MinDepth {
    public static void main(String[] args) {
        BinaryTree bTree = new BinaryTree();
//        bTree.root = bTree.buildBinaryTree();
        bTree.root = bTree.buildBinaryTreeComplex();
        int testMethod = 1;
        int result = 0;
        switch (testMethod) {
            case 1://通过递归计算二叉树的深度
                result = minDepth_1(bTree.root);
                break;
            case 2://通过层次遍历计算二叉树的深度
                result = minDepth_2(bTree.root);
                break;

        }

        System.out.println(result);
    }

    /**
     * 通过递归计算二叉树的最小
     *
     * @param root
     * @return
     */
    public static int minDepth_1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth_1(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth_1(root.right), min_depth);
        }
        return min_depth + 1;
    }

    /**
     * 通过层次遍历来求最大深度
     *
     * @return
     */
    public static int minDepth_2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<QueueNode> queue = new LinkedList<QueueNode>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode nodeDepth = queue.poll();
            TreeNode node = nodeDepth.node;
            int depth = nodeDepth.depth;
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }

        return 0;
    }

   static class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
