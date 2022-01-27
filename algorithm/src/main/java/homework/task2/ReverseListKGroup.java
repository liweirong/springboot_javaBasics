package homework.task2;

/**
 * 第三题：
 * 链表算法的菁华是反转问题，反转问题的珠峰是K个一组反转，请你参考讲义2.6.3实现个K个一组反转的功能
 * 下面给了10个数，请实现4个一组反转的功能，最后剩下不够一组的不用反转
 * 提示：
 * 你可以参考网上的材料和代码，只要你能理解，并且将下面的case运行出来就行
 * 要求：
 * 1.不得将元素放到数组或者栈等结构中，也不允许创建新链表，只允许操作链表
 * 2.请保证提交的代码能执行，不能执行或者执行结果不对，视为0分
 * 3.最晚提交时间：2022年1月3日晚12点，过期提交作废
 * 4.请将homework.task2下的三个题目做完后一起打包发到荣荣邮箱，包名必须有你的编号（参考编号列表），如果不按规定找不到你，视为0分
 * 5.出题不易，一起都为了你的学习，请不要随便外传题目
 */
public class ReverseListKGroup {

    public static void main(String[] args) {
        int[] score = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Node head = initLinkedList(score);

        Node newHead = reverseKGroup(head);
//        这里应该输出 4 3 2 1 8 7 6 5 9 10，最后的9 10不足一个分组就不用反转
        printList(newHead);

    }

    /**
     * todo 在这里实现功能
     *
     * @param head
     * @return
     */
    private static Node reverseKGroup(Node head) {


        //这里只是为了编译器不报错而让返回null，你可以根据情况修改
        return null;
    }


    /**
     * 输出链表
     *
     * @param head 头节点
     */
    public static String printList(Node head) {
        Node current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.val).append("\t");
            current = current.next;
        }
        return sb.toString();
    }

    /**
     * 初始化链表
     *
     * @param array
     * @return
     */
    private static Node initLinkedList(int[] array) {
        Node head = null, cur = null;
        for (int i = 0; i < array.length; i++) {
            Node newNode = new Node(array[i]);
            newNode.next = null;
            if (i == 0) {
                head = newNode;
                cur = head;
            } else {
                cur.next = newNode;
                cur = newNode;
            }
        }
        return head;
    }

    static class Node {
        public int val;
        public Node next;

        Node(int x) {
            val = x;
            next = null;
        }
    }
}
