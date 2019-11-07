package com.iris.springboot_all.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        System.out.println(longestCommonPrefix(new String[]{"aa", "aac", "aadasd"}));
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

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。(所有输入只包含小写字母 a-z)
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length <= 1) {
            return "";
        }
        Integer len = 1;
        String first = strs[0].substring(len);
        for (int i = 1; i < strs[0].length(); i++) {
            // 都开始变得不一样的时候返回
            int total = 0;
            for (int j = 1; j < strs.length; j++) {
                if (Objects.equals(first, strs[j].substring(len))) {
                    total++;
                }else{
                    return first;
                }


            }
            // 全部都有
            if (total == strs.length - 1) {
                first.join(strs[i].substring(i + 1));
                len++;
            }

        }

        return "";
    }
}
