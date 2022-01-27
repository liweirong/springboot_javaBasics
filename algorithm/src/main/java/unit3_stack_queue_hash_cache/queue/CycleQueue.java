package unit3_stack_queue_hash_cache.queue;

public class CycleQueue {
    private int maxSize;
    private int data[];
    private int front;
    /**
     * 这里rear指向最后一个数据的后面一个位置，即队列中有一个为空占位
     */
    private int rear;

    public CycleQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        front = 0;
        rear = 0;
    }

    /**
     * 判断队列是否已满
     * 因是循环队列，所以rear值可能小于front，所以不能使用rear == maxSize -1来判断
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int n) {
        if (isFull()) {
            System.out.println("队列已满，不能添加");
            return;
        }
        data[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public void head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        System.out.println("head=" + data[front]);
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int value = data[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("array" + (i % maxSize) + "=%d", data[i % maxSize]);
        }
    }

    /**
     * 因是循环队列，所以会出现rear<front情况，这里需要+maxSize
     *
     * @return
     */
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    public static void main(String[] args) {
        CycleQueue cycleQueue = new CycleQueue(3);
        cycleQueue.add(1);
        cycleQueue.add(2);
        cycleQueue.pop();
        cycleQueue.add(3);
        cycleQueue.print();
    }
}