package homework.task2;

/**
 * 第二题：
 * 假如我们算法课开班时为了了解学员的算法功底而进行了一次测评，假如成绩是{1,2,3,4,5,6,7,8,9,10}
 * 然后根据成绩好坏建立帮扶对子，成绩最好帮助成绩最差，也就是1和10，3和8，5和6，4和7，9和2这样的五个小组
 * 荣荣老师编排的时候发现单数位置不变(1,3,5,7,9)，偶数位置(10,8,6,4,2)反转,
 * 最后再拼接到一起就是{1 10 3 8 5 6 7 4 9 2},这样可以好了
 * 假如上述成绩都存在一个递增的单链表里，元素为偶`数个，请你帮助荣荣完成这个任务。
 * 提示：
 * 链表反转+链表合并
 * 要求：
 * 1.不得将元素放到数组或者栈等结构中，只允许操作链表
 * 2.可以分成两个链表，但是不可以创建新结点
 * 3.请保证提交的代码能执行，不能执行或者执行结果不对，视为0分
 * 4.最晚提交时间：2022年1月3日晚12点，过期提交作废
 * 5.请将homework.task2下的三个题目做完后一起打包发到荣荣邮箱，包名必须有你的编号（参考编号列表），如果不按规定找不到你，视为0分
 * 6.出题不易，一起都为了你的学习，请不要随便外传题目
 */
public class HelpEachOther {

    public static void main(String[] args) {
        int[] score = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Node head = initLinkedList(score);

//        Node newHead = helpEachOther(head);
//        这里应该输出 1 10 3 8 5 6 7 4 9 2
        System.out.println(printList(head));

    }

    /**
     * todo 在这里实现功能
     *
     * @param head
     * @return
     */
    private static Node helpEachOther(Node head) {


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
