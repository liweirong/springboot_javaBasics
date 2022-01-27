package unit5_tree;

import java.util.ArrayList;
import java.util.List;

public class BitTreeTravel {
    public static void main(String[] args) {
        BinaryTree bTree = new BinaryTree();
        bTree.root = bTree.buildBinaryTree();
//        bTree.root = bTree.buildBinaryTree();
        int testMethod = 2;

//        //基本遍历
//        switch (testMethod) {
//            case 1://前序
//                preOrderRecur(bTree.root);
//                break;
//            case 2:
//                inOrderRecur(bTree.root);
//                break;
//            case 3:
//                postOrderRecur(bTree.root);
//                break;
//        }

        List<Integer> res = new ArrayList<Integer>();
        switch (testMethod) {
            case 1://前序
                preOrder(bTree.root, res);
                break;
            case 2:
                inOrder(bTree.root, res);
                break;
            case 3:
                postOrder(bTree.root, res);
                break;
        }
        System.out.println(res);
    }

    /**
     * 基本的前序遍历
     *
     * @param head
     */
    public static void preOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 基本的中序遍历
     *
     * @param head
     */
    public static void inOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.val + " ");
        inOrderRecur(head.right);
    }

    /**
     * 基本的后序遍历
     *
     * @param head
     */
    public static void postOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.val + " ");
    }

    /**
     * 前序遍历，将结果返回到list中
     *
     * @param root
     * @param res
     */

    public static void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    /**
     * 中序遍历，将结果返回到list中
     *
     * @param root
     * @param res
     */

    public static void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    /**
     * 后序遍历，将结果返回到list中
     *
     * @param root
     * @param res
     */

    public static void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }

}
