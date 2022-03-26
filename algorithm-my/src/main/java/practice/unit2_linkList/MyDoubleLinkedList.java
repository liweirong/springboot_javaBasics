package practice.unit2_linkList;

/**
 * @author iris
 * @date 2022/3/13
 */
//设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针
///引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
//
// 在链表类中实现这些功能：
//
//
// get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
// addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
// addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
// addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val 的节点。如果 index 等于链表的长度，则该节点将附加
//到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
// deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
//
//
//
//
// 示例：
//
// MyLinkedList linkedList = new MyLinkedList();
//linkedList.addAtHead(1);
//linkedList.addAtTail(3);
//linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
//linkedList.get(1);            //返回2
//linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//linkedList.get(1);            //返回3
//
//
//
//
// 提示：
//
//
// 所有val值都在 [1, 1000] 之内。
// 操作次数将在 [1, 1000] 之内。
// 请不要使用内置的 LinkedList 库。
//
// Related Topics 设计 链表 👍 379 👎 0


// 707
public class MyDoubleLinkedList {
    public Node head, tail;
    public Integer size;

    static class Node {
        public Integer val;
        public Node pre;
        public Node next;

        public Node() {
        }

        public Node(Integer val) {
            this.val = val;
        }
    }

    public MyDoubleLinkedList() {
        head = new Node();
        tail = new Node();
        head.val = 0;
        tail.val = 0;
        head.next = tail;
        tail.pre = head;
        this.size = 0;
    }


    public int get(int index) {
        if (index >= size || index < 0) {
            return -1;
        }
        Node head = this.head.next;
        for (int i = 0; i <index ; i++) {
            head = head.next;
        }
        return head.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;
        ++size;

        Node find = this.head.next;
        for (int i = 0; i < index; i++) {
            find = find.next;
        }
        Node add = new Node(val);
        // 找到位置进行交换
        Node pre = find.pre;
        pre.next = add;
        add.pre = pre;
        add.next = find;
        find.pre = add;
    }

    public void deleteAtIndex(int index) {
        if (index >= size) return;
        if (index < 0) index = 0;
        --size;
        Node find = this.head.next;
        for (int i = 0; i < index; i++) {
            find = find.next;
        }
        // 找到位置进行交换
        find.next.pre = find.pre;
        find.pre.next = find.next;

        find.next = null;
        find.pre = null;

    }




}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
//leetcode submit region end(Prohibit modification and deletion)

