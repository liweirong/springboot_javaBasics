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
public class MyLinkedList {
    // 结点类
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    // 记录头结点/指针
    Node head;
    // 记录链表的长度(元素的个数) 因为下面要用到,用于判断index的合法性
    int size;


    public MyLinkedList() {
        // 初始化长度
        size = 0;
        // 初始化头结点 这里建立了一个虚拟头结点,这个节点指向头结点,
        // 现在因为没有头结点,所以指向null
        head = new Node(0);
    }

    public MyLinkedList(Node head) {
        Node sizeNode = head;
        // 初始化长度
        while (sizeNode.next != null) {
            sizeNode = sizeNode.next;
            ++size;
        }
        this.head = head;
    }


    public int get(int index) {
        //判断index的合法新
        if (index < 0 || index >= size) {
            return -1;
        }
        //建立一个临时节点来遍历链表找到目标位置的节点
        Node temp = this.head;
        for (int i = 0; i <= index; i++) {
            //不到目标位置,则临时节点向后移动,知道移动到指定位置
            temp = temp.next;
        }
        return temp.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        // If index is greater than the length,
        // the node will not be inserted.
        if (index > size) return;

        // [so weird] If index is negative,
        // the node will be inserted at the head of the list.
        if (index < 0) index = 0;

        ++size;
        // find predecessor of the node to be added
        Node pred = head;
        for (int i = 0; i < index; ++i) pred = pred.next;

        // node to be added
        Node toAdd = new Node(val);
        // insertion itself
        toAdd.next = pred.next;
        pred.next = toAdd;

    }

    public void deleteAtIndex(int index) {
        // If index is greater than the length,
        // the node will not be inserted.
        if (index >= size || index < 0) return;
        --size;
        // find predecessor of the node to be added
        Node temp = head;
        //这里跟上面一样,只要找到要删除位置的前驱指针,
        //因为有虚拟头指针的存在所以实际头指针也有前驱
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        //找到后进行插入操作
        temp.next = temp.next.next;
    }

    public MyLinkedList reverseKGroup(int k) {
        System.out.println(k + "个一组翻转");
        // 虚拟节点
        Node dummy = new Node(0);
        dummy.next = this.head.next;

        // pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
        // pre        start         end     end.next
        // node1  |   node2 node3 node4   | node5
        Node pre = dummy;
        Node end = dummy;
        // 后继 next = end.next 不为空
        while (end.next != null) {
            // 取一组 k个
            for (int i = 0; i < k && end != null; i++)
                end = end.next;
            // 反转到最后一个或不够k个退出
            if (end == null) break;

            Node start = pre.next;
            // 标记下一个区间 因为要暂时断掉连接
            Node next = end.next;
            end.next = null;
            // 反转区间k个
            pre.next = reverse(start);

            // 准备下次循环
            start.next = next;
            pre = start;
            end = pre;
        }
        return new MyLinkedList(dummy);
    }

    private Node reverse(Node head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.next == null) {
            return head;
        }
        // 前一个节点指针
        Node pre = null;
        // 当前节点指针
        Node curr = head;
        // pre -> curr -> next
        while (curr != null) {
            // 保留下个节信息准备进行反转
            Node next = curr.next;
            // pre <- curr  next
            // 1 <- 2  3
            curr.next = pre;

            //        pre curr  next
            //null <- 1 <- 2   3  ->   4-> null
            pre = curr;
            curr = next;
        }
        return pre;
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

