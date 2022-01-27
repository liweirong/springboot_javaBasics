package unit5_tree;

import java.util.*;

public class Level_Travel_Element {


    public static void main(String[] args) {
        BinaryTree bTree = new BinaryTree();
        bTree.root = bTree.buildBinaryTree();
        int testMethod = 3;
        List<Integer> res = null;
        List<Double> res2 = null;
        switch (testMethod) {
            case 1://LeetCode 515 在每个树行中找最大值
                res = largestValues(bTree.root);
                System.out.println(res);
                break;
            case 2://LeetCode 637 在每个树行中找平均值
                res2 = averageOfLevels(bTree.root);
                System.out.println(res2);
                break;
            case 3://LeetCode 199 二叉树的右视图
                res = rightSideView(bTree.root);
                System.out.println(res);
                break;

        }

    }

    /**
     * LeetCode 515 在每个树行中找最大值
     *
     * @param root
     * @return
     */
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();

        if (root != null) {
            deque.addLast(root);
        }

        while (!deque.isEmpty()) {
            int size = deque.size();
            int levelMaxNum = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.poll();
                levelMaxNum = Math.max(node.val, levelMaxNum);
                if (node.left != null) deque.addLast(node.left);
                if (node.right != null) deque.addLast(node.right);
            }
            res.add(levelMaxNum);
        }
        return res;
    }

    /**
     * LeetCode 637 在每个树行中找平均值
     *
     * @param root
     * @return
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<Double>();
        if (root == null) return res;
        Queue<TreeNode> list = new LinkedList<TreeNode>();
        list.add(root);
        while (list.size() != 0) {
            int len = list.size();
            double sum = 0;
            for (int i = 0; i < len; i++) {
                TreeNode node = list.poll();
                sum += node.val;
                if (node.left != null) list.add(node.left);
                if (node.right != null) list.add(node.right);
            }
            res.add(sum / len);
        }
        return res;
    }

    /**
     * LeetCode 199 二叉树的右视图
     *
     * @param root
     * @return
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {  //将当前层的最后一个节点放入结果列表
                    res.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
        }
        return res;
    }
}

