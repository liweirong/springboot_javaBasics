package unit5_tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode104 最大深度和最小深度问题
 */
public class MaxDepth {
    public static void main(String[] args) {
        BinaryTree bTree = new BinaryTree();
        bTree.root = bTree.buildBinaryTree();
        bTree.root = bTree.buildBinaryTreeComplex();
        int testMethod = 3;
        int result = 0;
        switch (testMethod) {
            case 1://通过递归计算二叉树的深度
                result = maxDepth_1(bTree.root);
                break;
            case 2://通过层次遍历计算二叉树的深度
                result = maxDepth_2(bTree.root);
                break;
            case 3: //N叉树的最大深度问题
                NTree nTree = new NTree();
                nTree.root = nTree.buildNTree();
                result = maxDepth_N(nTree.root);
        }

        System.out.println(result);
    }

    /**
     * 通过递归计算二叉树的深度
     *
     * @param root
     * @return
     */
    public static int maxDepth_1(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth_1(root.left);
            int rightHeight = maxDepth_1(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 通过层次遍历来求最大深度
     *
     * @param root
     * @return
     */
    public static int maxDepth_2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            //size表示某一层的所有元素数
            int size = queue.size();
            //size=0 表示一层访问完了
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    /**
     * N叉树的最大深度问题
     * @param root
     * @return
     */
    public static int maxDepth_N(NTreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.children==null || root.children.isEmpty()) {
            return 1;
        } else {
            List<Integer> heights = new LinkedList<Integer>();
            for (NTreeNode item : root.children) {
                heights.add(maxDepth_N(item));
            }
            return Collections.max(heights) + 1;
        }
    }
}
