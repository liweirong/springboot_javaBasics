package unit3_stack_queue_hash_cache.queue;

class ArrayQueue {
    private int[] data; //队列中存放的数据
    private int maxSize; //队列的大小
    private int front;//指向队列头部的指针
    private int rear; //指向队列尾部的指针

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        front = -1;
        rear = -1;
    }

    /**
     * 判断队列是否已满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     */
    public void add(int n) {
        if (isFull()) {
            System.out.println("队列已满，不能添加");
            return;
        }
        data[++rear] = n;
    }

    /**
     * 显示头部数据
     */
    public void head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        System.out.println(data[front + 1]);
    }

    /**
     * 取出头部数据
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int a = data[++front];
        data[front] = 0;
        return a;
    }

    /**
     * 打印全部数据
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < data.length; i++) {
            System.out.printf("array[" + i + "]=%d\n", data[i]);
        }
    }

    public static void main(String[] args) {
        //1.声明一个可以存储6个元素的顺序队列，默认值为0,front 和rear指针为-1
        ArrayQueue queue = new ArrayQueue(6);
        //2.向顺序队列中添加元素
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        //2.1打印当前队列元素
        queue.print();
        //3.将顺序队列中元素取出
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();
        //4.队列中元素全部取出
    }
}
