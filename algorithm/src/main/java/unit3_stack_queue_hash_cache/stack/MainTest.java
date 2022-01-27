package unit3_stack_queue_hash_cache.stack;

import java.util.Stack;

public class MainTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("栈顶元素为：" + stack.peek());
        while (!stack.empty()) {
            //只显示没出栈
            System.out.println(stack.peek());
            //出栈并且显示
            System.out.println(stack.pop());
        }
    }
}
