package practice.unit5_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author iris
 * @date 2022/3/24
 */
public class TreeBase {
    public static void main(String[] args) {
        TreeNode init = init();
        // 先序遍历
        System.out.println(preTree(init));
        // 中序遍历
        // 后序遍历
    }

    /**
     * 构造一个相对复杂的二叉树
     *
     * @return
     */
    public static TreeNode init() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.left.right = new TreeNode(4);
        node.left.left.right.right = new TreeNode(5);
        node.left.left.right.right.left = new TreeNode(6);
        node.left.left.right.right.left.left = new TreeNode(8);
        node.left.left.right.right.left.right = new TreeNode(8);

        node.right = new TreeNode(9);
        node.right.left = new TreeNode(10);
        node.right.right = new TreeNode(11);
        node.right.right.right = new TreeNode(12);
        node.right.right.right.left = new TreeNode(13);
        node.right.right.right.left.left = new TreeNode(15);
        node.right.right.right.left.right = new TreeNode(14);
        return node;

    }

    /**
     * 树的前序遍历
     * 前序遍历是中左右，每次先处理的是中间节点
     * 那么先将跟节点放⼊栈中，然后将右孩⼦加⼊栈，再加 ⼊左孩⼦。
     * 为什么要先加⼊右孩⼦，再加⼊左孩⼦呢? 因为这样出栈的时候才是中左右的顺序。
     * 不难写出如下代码: (注意代码中空节点不⼊栈)
     */
    private static List<Integer> preTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            //
            while (node != null) {
                result.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return result;
    }
}




