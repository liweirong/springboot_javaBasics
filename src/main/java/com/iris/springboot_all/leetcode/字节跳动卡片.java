package com.iris.springboot_all.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 字节跳动算法实现
 *
 * @author iris
 * @date 2019/10/23
 */
public class 字节跳动卡片 {
    public static void main(String[] args) {
        //1
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    /**
     * 最长字串:给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
     * <p>
     * 分析：遍历该字符串，每遍历一个字母时，利用map去找该字母最近一次出现是什么时候，中间这一段便是无重复字符的字符串。
     * O(n) 时间
     */
    public static int lengthOfLongestSubstring(String s) {
        //空返回0
        if ("".equals(s) || s == null) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int result = 0;
        Map<Character, Integer> m = new HashMap<>();
        int start = 1;
        for (int i = 1; i <= arr.length; i++) {
            char c = arr[i - 1];
            if (m.get(c) != null && m.get(c) >= start) {
                start = m.get(c) + 1;
                m.put(c, i);
            } else {
                m.put(c, i);
                result = Math.max(result, i - start + 1);
            }
        }
        return result;
    }
}
