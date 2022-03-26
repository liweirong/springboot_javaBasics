package practice.unit2_linkList;

import com.alibaba.fastjson.JSONObject;

/**
 * @author iris
 * @date 2022/3/13
 */
public class LinkTest {
    public static void main(String[] args) {
        MyLinkedList linkList = new MyLinkedList();
        linkList.addAtTail(1);
        linkList.addAtTail(2);
        linkList.addAtTail(3);
        linkList.addAtTail(4);
        System.out.println(printLink(linkList));
        System.out.println(printLink(linkList.reverseKGroup(2)));
        MyDoubleLinkedList linkedList = new MyDoubleLinkedList();
        linkedList.addAtHead(7);
        linkedList.addAtHead(2);
        linkedList.addAtHead(1);
        linkedList.addAtIndex(3, 0);
        linkedList.deleteAtIndex(2);
        linkedList.addAtHead(6);
        linkedList.addAtTail(4);
        System.out.println(linkedList.get(4) + " 4");
        linkedList.addAtHead(4);  //现在链表是1-> 3
        linkedList.addAtIndex(5, 0);  //现在链表是1-> 3
        linkedList.addAtHead(6);  //现在链表是1-> 3
        System.out.println(printLink(linkedList) + "  ");
//        linkedList.addAtHead(1);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(7);
//        linkedList.addAtHead(3);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(2);
//        linkedList.addAtTail(5);
//        System.out.println(linkedList.get(5));
//        System.out.println(printLink(linkedList) + " // ");
//        linkedList.deleteAtIndex(6);
//        linkedList.deleteAtIndex(4);
//        System.out.println(printLink(linkedList) + " // ");
    }

    private static String printLink(MyLinkedList linkedList) {
        if (linkedList.size == 0) {
            return "{}";
        } else {
            System.out.println("链表大小：" + linkedList.size);
        }
        MyLinkedList.Node head = linkedList.head;
        StringBuilder stringBuilder = new StringBuilder();
        while (head.next != null) {
            head = head.next;
            stringBuilder.append(head.val).append("-> ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 3);
    }

    private static String printLink(MyDoubleLinkedList linkedList) {
        if (linkedList.size == 0) {
            return "{}";
        }
        MyDoubleLinkedList.Node head = linkedList.head;
        StringBuilder stringBuilder = new StringBuilder();
        while (head.next != null) {
            head = head.next;
            stringBuilder.append(head.val).append("= ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 3);
    }
}
