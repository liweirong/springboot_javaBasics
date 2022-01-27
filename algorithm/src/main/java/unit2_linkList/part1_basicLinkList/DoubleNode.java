package unit2_linkList.part1_basicLinkList;

public class DoubleNode {
    public int data;    //数据域
    public DoubleNode next;    //指向下一个结点
    public DoubleNode prev;

    public DoubleNode(int data) {
        this.data = data;
    }

    //打印结点的数据域
    public void displayNode() {
        System.out.print("{" + data + "} ");
    }
}
