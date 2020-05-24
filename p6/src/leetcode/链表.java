package leetcode;

import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

/**
 * 方法1：先反转链表再遍历
 *
 * @author iris
 * @date 2020/4/13
 */
public class 链表 {

    public static void main(String[] args) {
        float a=1.3F;
        float b= 1.2F+0.1F;
        System.out.println(b);// 1.3000001
        System.out.println(Math.abs(a - b) < 0.000001);


        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        //listNode = null;
        printNode(listNode);
        ArrayList<Integer> result = new 链表().printListFromTailToHead(listNode);
        System.out.println(result);


    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        listNode = reverseListByLocal(listNode);
        if (listNode == null) {
            return list;
        }
        /*反转链表*/
        System.out.print("反转链表：");
        printNode(listNode);

        ListNode resultList = new ListNode(-1);
        resultList.next = listNode;
        ListNode p = listNode;
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        return list;
    }


    /**
     * leetcode反转链表 迭代、递归
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }


    /**
     * 就地反转链表 1 2 3 -> 3 2 1
     *
     * @param listNode
     * @return
     */
    private ListNode reverseListByLocal(ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        ListNode resultList = new ListNode(-1);
        resultList.next = listNode; // {-1,1,2,3}
        ListNode p = listNode; // {1,2,3}
        ListNode pNext = p.next; // 2
        System.out.println(1);
        printNode(resultList);
        while (pNext != null) {
            p.next = pNext.next; // 2=3
            pNext.next = resultList.next; // 3 = 1
            resultList.next = pNext; // 1 = 2
            pNext = p.next; // 2 = 3
            printNode(resultList);
        }
        printNode(resultList);
        return resultList.next;
    }

    private static void printNode(ListNode listNode) {
        System.out.print("{");
        while (listNode != null) {
            if (listNode.next == null) {
                System.out.print(listNode.val);
            } else {
                System.out.print(listNode.val + ",");
            }
            listNode = listNode.next;

        }
        System.out.println("}");
    }
}

