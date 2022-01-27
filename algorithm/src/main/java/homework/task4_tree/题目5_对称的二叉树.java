package homework.task4_tree;


/**
 * 树和递归讲义 4.5的我们研究了一个二叉树是否为对称、两棵二叉树是否相等等问题，在【5】我们造了一道题，怎么判断两个树是否对称。
 * 现在就请你来解决这个问题
 *
 */

public class 题目5_对称的二叉树 {
    public static void main(String[] args) {
        BinaryTree binaryTree1 = new BinaryTree();
        binaryTree1.root = binaryTree1.buildBinaryTree();

        BinaryTree binaryTree2 = new BinaryTree();
        binaryTree2.root = binaryTree2.buildBinaryTree2();

        //todo 这里实现你的方法
        Boolean result = symmetry(binaryTree1.root,binaryTree2.root);
        System.out.println(result);

    }

    /**
     * 这里实现你的方法
     * @param root1 第一棵树
     * @param root2 第二棵树
     * @return
     */

    public static Boolean symmetry(TreeNode root1, TreeNode root2) {

//        这里只是为了编译通过而设置，你可以修改
        return false;
    }




//    public static TreeNode buildBinaryTree() {
//        TreeNode node = new TreeNode(3);
//        node.left = new TreeNode(9);
//        node.right = new TreeNode(20);
//        node.right.right = new TreeNode(7);
//        node.right.left = new TreeNode(15);
//        return node;
//
//    }
}
