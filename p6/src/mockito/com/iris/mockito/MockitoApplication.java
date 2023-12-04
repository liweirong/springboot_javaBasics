package mockito.com.iris.mockito;


import mockito.com.iris.mockito.entity.User;
import mockito.com.iris.mockito.mapper.UserMapper;
import mockito.com.iris.mockito.service.UserService;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.SpringApplication;

import java.util.*;

import static org.mockito.Mockito.when;

/**
 * @author iris
 * @date 2023/5/25
 */

public class MockitoApplication {

    public static void main(String[] args) {
//        testGetUserById();
//        System.out.println(findMedianSortedArrays(new int[]{1,3},new int[]{2}));
//        System.out.println(maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
//        System.out.println(maxAreaOfIsland(new int[][]{
//                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}));

//        System.out.println(coinChange(new int[]{1,2,5},12));
        System.out.println(longestPalindrome("aaaa"));
    }

    public static String longestPalindrome(String s) {
        int start = 0, end =1;
        int max = 1;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            // 初始化对角线，都是单个的所以肯定是回文
            dp[i][i] = 1;
        }
        for (int j = 1; j < s.length(); j++) {
            for (int i = 1; i < s.length(); i++) {
            // 从k开始，因为字符串的起点比终点小
                if (i>j ||s.charAt(i) != s.charAt(j)) {
                    continue;
                }
                // 相等的话需要看内圈 左下角
                if (dp[i + 1][j - 1] == 1
                        // 少于两位则也算回文
                        || (j+1)-i<=2) {
                    dp[i][j] = 1;
                    if (j + 1 - i > max) {
                        max = j + 1 - i;
                        System.out.println(i + "," + j + "是回文" + s.substring(i, j + 1) + ",max:" + max);
                        start = i;
                        end = j + 1;
                    }
                }
            }
        }


        return s.substring(start, end);

    }

    public static int coinChange(int[] coins, int amount) {
        // 最优解的上一个也是最优解
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                // 大于硬币最小值
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    System.out.println("i：" + i + ",j：" + j + "-  dp[i]:" + dp[i]);
                }
            }

        }
        for (int i = 0; i < amount + 1; i++) {
            System.out.print(dp[i] + "、");
        }
        return dp[amount] > amount ? -1 : dp[amount];

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        int mid = size / 2;
        Stack<Integer> stack = new Stack<>();
        if (size % 2 == 0) {
            // 偶数
            mid = mid - 1;
        }
        int a = nums1.length - 1;
        int b = nums2.length - 1;


        for (int i = size - 1; i >= mid; i--) {
            if (a < 0) {
                stack.add(nums2[b--]);
            } else if (b < 0) {
                stack.add(nums1[a--]);
            } else {
                if (nums1[a] > nums2[b]) {
                    stack.add(nums1[a--]);
                } else {
                    stack.add(nums2[b--]);
                }
            }
        }
        if (size % 2 == 0) {
            // 偶数
            return (double) (stack.pop() + stack.pop()) / 2;
        } else {
            return (double) stack.pop();
        }


    }

//    public static void testGetUserById() {
//        UserMapper userDao = Mockito.mock(UserMapper.class);
//        when(userDao.getUserById(1)).thenReturn(new User(1, "John"));
//        UserService userService = new UserService(userDao);
//        User user = userService.getUserById(1);
//        System.out.println(user);
//
//
//        // 进阶 使用Mockito进行异步测试
//        UserService service = Mockito.mock(UserService.class);
//        when(service.getUserById(1)).thenAnswer(new Answer<Void>() {
//            @Override
//            public Void answer(InvocationOnMock invocation) throws Throwable {
//                Object[] args = invocation.getArguments();
//                String arg1 = (String) args[0];
//                Consumer<String> callback = (Consumer<String>) args[1];
//                callback.accept(arg1 + " is done");
//                return null;
//            }
//        });
//        MyAsyncClient client = new MyAsyncClient(service);
//        String result = client.doSomething("test");
//        assertEquals("test is done", result);
//    }


    /**
     * [1,2,4,2,5,7,2,4,9,0]
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        // 遍历递增，list进行存储，最后排序取出第k个
        int start = prices[0];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < prices.length - 1; i++) {
            int day = prices[i];
            // 相邻的开始递减则开始卖
            int nextPrice = prices[i + 1];
            if (nextPrice < day) {
                // 开始递减 卖
                int e = day - start;
                if (e < 0) {
                    continue;
                }
                result.add(e);
                start = nextPrice;
                System.out.println("开始递减 卖" + i + "--" + start);
            } else {
                if (i == prices.length - 2) {
                    // 收尾 开始递减 卖
                    int e = nextPrice - start;
                    if (e < 0) {
                        continue;
                    }
                    result.add(e);
                    System.out.println("收尾 卖" + i + "--" + start);
                }
            }
        }

        Collections.sort(result);
        System.out.println(result);
        int[] resultC = new int[2];

        if (result.size() == 0) {

        } else if (result.size() == 1) {
            resultC[1] = result.get(0);
        } else {
            resultC[1] = result.get(result.size() - 1);
            resultC[0] = result.get(result.size() - 2);
        }
        return resultC[0] + resultC[1];
    }


    public static int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    System.out.println("岛屿面积：" + area);
                    result = Math.max(result, area);
                }
            }
        }
        return result;

    }

    // 基本的 DFS 框架：每次搜索四个相邻方格
    private static int dfs(int[][] grid, int r, int c) {
        // 边界返回
        if (r < 0 || c < 0
                || r >= grid.length
                || c >= grid[0].length) {
            return 0;
        }
        // 若该方格是岛屿边界，返回
        if (grid[r][c] == 0) {
            return 0;
        }
        int area = 0;
        // 若该方格是岛屿，面积加 1
        if (grid[r][c] == 1) {
            area++;
        }
        // 因为是dfs，下次遍历时候要置为遍历状态，否则会重复计算
        grid[r][c] = 0; // 将方格标记为"已遍历"


        area += dfs(grid, r - 1, c); // 上边相邻
        area += dfs(grid, r + 1, c); // 下边相邻
        area += dfs(grid, r, c - 1); // 左边相邻
        area += dfs(grid, r, c + 1); // 右边相邻
        return area;
    }
}
