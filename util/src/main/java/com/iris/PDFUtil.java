package com.iris;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.core.io.DefaultResourceLoader;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

/**
 * @author iris
 * @date 2022/11/4
 */
public class PDFUtil implements Callback {

    @Autowired
    private Object o;
    @Resource(name = "aa")
    private Object oo;

    public static void main(String[] args) {
        System.out.println(mostFrequentEven(new int[]{1000}));
        System.out.println(JSONObject.toJSONString(twoSum(new int[]{-1, 0}, -1)));

        Enhancer enhancer = new Enhancer();
        PDFUtil pdfUtil = new PDFUtil();
        enhancer.setSuperclass(pdfUtil.getClass());
        enhancer.setCallback(new QR());
        PDFUtil x = (PDFUtil) enhancer.create();
        x.test();

        System.out.println(isValid("(])"));
        System.out.println(scoreOfParentheses("()(((()))())"));
        System.out.println(scoreOfParenthesesa("()(((()))())"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(maxDepth("(1+(2*3)+((8)/4))+1"));
    }

    public static boolean isValid(String s) {
        // 存左括号
        Stack<Character> stack1 = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char peek1 = chars[i];
            if ('(' == peek1 || '[' == peek1 || '{' == peek1) {
                stack1.push(peek1);
            } else {
                if (stack1.isEmpty()) {
                    // 直接来右括号
                    return false;
                }
                // 弹出不合预期
                if (')' == peek1 && stack1.pop() != '(') {
                    return false;
                }
                if (']' == peek1 && stack1.pop() != '[') {
                    return false;
                }
                if ('}' == peek1 && stack1.pop() != '{') {
                    return false;
                }
            }
        }

        return stack1.isEmpty();
    }

    /**
     * () = 1 (())
     *
     * @param s
     * @return
     */
    public static int scoreOfParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
//                deque.addLast('(');
                stack.push('(');
            } else {
                char c = stack.pop();
                // 弹出来是（）匹配的就是1
                if (c == '(') {
                    stack.push('1');
                } else {
                    // 数字的话进行转换
                    int sum = (c - '0');
                    // 继续弹 不是左括号闭合的话
                    while ((c = stack.pop()) != '(') {
                        sum += (c - '0');
                    }
                    stack.push((char) ((sum << 1) + '0'));
                }
            }
        }
        int result = 0;
        System.out.println(stack.size());
        while (!stack.isEmpty()) {
            // 累加
            result += (stack.pop() - '0');
        }
        return result;
    }


    public static int scoreOfParenthesesa(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                // 右括号
                Character pop = stack.pop();
                if (pop == '(') {
                    // 立马匹配 则记一分
                    stack.push('1');
                } else {
                    // 不是则代表是数字进行累加
                    int sum = (pop - '0');

                    while ((pop = stack.pop()) != '(') {
                        sum += (pop - '0');
                    }
                    // 塞回计算结果需要翻两倍
                    stack.push((char) ((sum << 1) + '0'));
                }
            }
        }


        int res = 0;
        // 结果进行统计
        while (!stack.isEmpty()) {
            res += (stack.pop() - '0');
        }
        return res;
    }

    public static int longestValidParentheses(String s) {
        int start = 0;
        int max = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 栈存左括号下标
                stack.push(i);
            } else {
                // 遇到右括号弹出一个右括号进行配对
                if (stack.isEmpty()) {
                    start = i + 1;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        // 栈空了就表示前面都是连续的，记录下这个下标+1 和最大值比较
                        max = Math.max(max, i - start + 1);
                    } else {
                        // 不空表示前面不规整看下上一个不规整的角标
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }
        return max;

    }

    public static int[] maxDepthAfterSplit(String seq) {
        /**
         * 括号序列   ( ( ) ( ( ) ) ( ) )
         * 下标编号   0 1 2 3 4 5 6 7 8 9
         * 嵌套深度   1 2 2 2 3 3 2 2 2 1
         */
        int d = 0;
        int length = seq.length();
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            if (seq.charAt(i) == '(') {
                // 栈深度+1
                ++d;
                ans[i] = d % 2;
            } else {
                ans[i] = d % 2;
                --d;
            }
        }
        return ans;
    }

    public static int maxDepth(String s) {
        Stack<Character> stack = new Stack<>();
        int max = 0;
        // 都是有效括号 栈的最大深度就是大的嵌套
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ('(' == c) {
                // 左括号入栈
                stack.push(c);
            } else if (')' == c) {
                // 进来看下左括号的深度
                max = Math.max(max, stack.size());
                stack.pop();
            }
        }
        return max;

    }

    public static int minInsertions(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            if (c == '(') {
                // 遇到左括号直接入栈
                stack.push('(');
                continue;
            }
            /**
             * 2 字符为‘）’
             * 1）当前为最后一个字符
             * 1⃣️栈为空，那么需要拼接一个‘（’和一个‘）’，所以ans += 2
             * 2⃣️栈不为空，那么需要拼接一个‘）’，所以ans += 1，同时出栈
             * 2）当前不为最后一个字符
             * 1⃣️后面一个字符为‘）’
             * (1) 栈为空，那么需要拼接一个‘（‘，所以ans += 1
             * (2) 栈不为空，那么不需要拼接字符，只需出栈即可，注意i++
             * 2⃣️后面一个字符不为‘）’
             * (1) 栈为空，那么需要拼接一个‘（‘和一个‘）’，所以ans += 2
             * (2) 栈不为空，那么需要拼接一个‘）’，只需出栈即可，由于下一个是‘（’，所以直接i++即可
             * 最后就是加上栈中‘（’数量 * 2即可

             */
            if (i + 1 == chars.length) {
                // 1）当前为最后一个字符
                if (stack.isEmpty())
                    // 栈为空，那么需要拼接一个‘（’和一个‘）’，所以ans += 2
                    ans += 2;
                else {
                    stack.pop();
                    // 栈不为空，那么需要拼接一个‘）’，所以ans += 1，同时出栈
                    ans += 1;
                }
            } else {
                // 2）当前不为最后一个字符
                // 后一个字符为‘）’
                if (s.charAt(i + 1) == ')') {
                    if (stack.isEmpty()) {
                        // 如果栈空了需要补一个'('
                        ans++;
                    } else {
                        // 弹出一个匹配的'('
                        stack.pop();
                    }
                    i++;
                } else {
                    // 后面一个字符不为‘）’
                    if (stack.isEmpty()) {
                        // 栈为空，那么需要拼接一个‘（‘和一个‘）’，所以ans += 2
                        ans += 2;
                    } else {
                        // 栈不为空，那么需要拼接一个‘）’，只需出栈即可，由于下一个是‘（’，所以直接i++即可
                        ans++;
                        i++;
                    }
                }
            }
        }

        // 栈里面剩余的是左括号
        ans += stack.size() * 2;
        return ans;
    }

    private void test() {
        System.out.println("test");
    }

    public static int[] twoSum(int[] numbers, int target) {
        int headIndex = 0;
        int tailIndex = numbers.length - 1;

        while (headIndex < tailIndex) {
            int sum = numbers[headIndex] + numbers[tailIndex];
            if (sum > target) {
                tailIndex--;
            } else if (sum < target) {
                headIndex++;
            } else {
                return new int[]{headIndex, tailIndex};
            }
        }

        return null;

    }

    public static int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        // 计数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 1 && (nums[i] % 2) == 0) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
        }
        System.out.println("map:" + JSONObject.toJSONString(map));
        final int[] result = {-1};
        final int[] max = {-1};
        map.forEach((key, value) -> {
            if (max[0] < value) {
                max[0] = value;
                result[0] = key;
            } else if (max[0] == value) {
                // 一样最大值时取最小偶数
                result[0] = Math.min(key, result[0]);
            }
        });

        return result[0];

    }

}
