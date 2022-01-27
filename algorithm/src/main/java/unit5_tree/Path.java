package unit5_tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 路径相关问题
 */
public class Path {
    public static void main(String[] args) {
        BinaryTree bTree = new BinaryTree();
        bTree.root = bTree.buildBinaryTree();

        int testMethod = 3;
        switch (testMethod) {
            case 1://LeetCode257 二叉树的所有路径
                List<String> result = binaryTreePaths_1(bTree.root);
                System.out.println(result);
                break;
            case 2:
                boolean result1 = hasPathSum(bTree.root, 12);
                System.out.println(result1);
                break;
            case 3:
                List<List<Integer>> pathSum = pathSum(bTree.root, 12);
                System.out.println(pathSum);


        }


    }

    /**
     * 问题1：LeetCode257 二叉树的所有路径
     *
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths_1(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }

    private static void dfs(TreeNode root, String path, List<String> res) {
        //如果为空，直接返回
        if (root == null)
            return;
        //如果是叶子节点，说明找到了一条路径，把它加入到res中
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
            return;
        }
        //如果不是叶子节点，在分别遍历他的左右子节点
        dfs(root.left, path + root.val + "->", res);
        dfs(root.right, path + root.val + "->", res);
    }

    /**
     * 问题2：LeetCode112. 路径总和
     *
     * @param root
     * @param sum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


    /**
     * 问题3  LeetCode113 路径总和II
     */
    static List<List<Integer>> ret = new LinkedList<List<Integer>>();
    static Deque<Integer> path = new LinkedList<Integer>();

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ret;
    }

    public static void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        path.pollLast();
    }

}
