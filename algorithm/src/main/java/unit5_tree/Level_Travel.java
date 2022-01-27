package unit5_tree;

import java.util.*;

public class Level_Travel {


    public static void main(String[] args) {
        BinaryTree bTree = new BinaryTree();
        bTree.root = bTree.buildBinaryTree();
        int testMethod = 1;
        List<List<Integer>> res = null;
        switch (testMethod) {
            case 1: //仅仅遍历并输出一遍，不考虑分层的问题
                List<Integer> simple = simpleLevelOrder(bTree.root);
                System.out.println(simple);
                break;
            case 2://leetcode102  不同的层的结点要分行显示
                res = level102Order(bTree.root);
                break;
            case 3://LeetCode 107 层序遍历-自底向上
                res = levelOrderBottom(bTree.root);
                break;
            case 4://LeetCode 429 二叉树的锯齿形层序遍历
                res = zigzagLevelOrder(bTree.root);
                break;

        }
        System.out.println(res);

    }

    /**
     * 简单的层次遍历：仅仅遍历并输出一遍，不考虑分层的问题
     * 应输出结果 [3, 9, 20, 15, 7]
     *
     * @param root
     * @return
     */
    public static List<Integer> simpleLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }

        List<Integer> res = new ArrayList<Integer>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点放入队列中，然后不断遍历队列
        queue.add(root);
        //有多少元素执行多少次
        while (queue.size() > 0) {
            //获取当前队列的长度，这个长度相当于 当前这一层的节点个数
            TreeNode t = queue.remove();
            res.add(t.val);
            if (t.left != null) {
                queue.add(t.left);
            }
            if (t.right != null) {
                queue.add(t.right);
            }

        }
        return res;
    }


    /**
     * leetcode102 二叉树的层序遍历
     * 不同的层的结点要分行显示
     * 应输出结果: [[3], [9, 20], [15, 7]]
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> level102Order(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点放入队列中，然后不断遍历队列
        queue.add(root);

        //有几层执行几次
        while (queue.size() > 0) {
            //获取当前队列的长度，这个长度相当于 当前这一层的节点个数
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            //将队列中的元素都拿出来(也就是获取这一层的节点)，放到临时list中
            //如果节点的左/右子树不为空，也放入队列中
            //处理每一层
            for (int i = 0; i < size; ++i) {
                TreeNode t = queue.remove();
                tmp.add(t.val);
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
            }
            //将临时list加入最终返回结果中
            res.add(tmp);
        }
        return res;
    }

    /**
     * LeetCode 107 层序遍历-自底向上
     * 应输出结果 [[15, 7], [9, 20], [3]]
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new LinkedList<List<Integer>>();
        if (root == null) {
            return levelOrder;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        //有几层处理几次
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            //处理一层
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            //反序
            levelOrder.add(0, level);
        }
        return levelOrder;
    }

    /**
     * LeetCode429 二叉树的锯齿形层序遍历
     * 输出结果为： [[3], [20, 9], [15, 7]]
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;
        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }
}

