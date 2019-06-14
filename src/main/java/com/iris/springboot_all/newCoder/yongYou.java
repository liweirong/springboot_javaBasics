package com.iris.springboot_all.newCoder;

/**
 *1.一架飞机载着5位运动员从奥林匹克运动会归来，这5位运动员在某个项目中排名第一到第五。他们说了下面这些话：
 * A:”我不是最后一名。”
 * B:”C是第三名。”
 * C：”A的排名在E后面。”
 * D:”E是第二名。”
 * E:”D不是第一名。”
 * 处于谦虚或其他什么原因，金牌和银牌的得主都说了谎。那三个成绩相对较差的运动员倒说了真话。 他们的排名到底怎样？
 *
 *
 * 2.说出ArrayList,Vector, LinkedList的存储性能和特性？
 * 3.说明Jsp中errorPage的作用，如何应用？
 * 4.sleep( ) 和 wait( ) 有什么区别?
 * 5.forward和redirect的区别？
 * @author iris
 * @date 2019/6/15
 */
public class yongYou {

    public static void main(String[] args) {
        System.out.println(1);
    }​

    /**
     * 给定如下的n*n的数字矩阵，每行从左到右是严格递增， 每列的数据也是严格递增
     * <p>
     * 1  2  3
     * <p>
     * 3  5  6
     * <p>
     * 4  8  9
     * <p>
     * 现在要求设计一个算法， 给定一个数k 判断出k是否在这个矩阵中。描述算法并且给出时间复杂度（不考虑载入矩阵的消耗）
     *
     * @param arrs
     * @return
     */
    private Boolean checkInArrays(Integer[][] arrs, Integer k) {
        ​int len = arrs.length;
        int nlen = arrs[0].length;
        for (int i = 0; i < len; i++) {
            // 在这个行列
            if (k <= arrs[i][nlen] && k >= arrs[i][0]) {
                for (int j = 0; j < nlen; j++) {
                    if (k.equals(arrs[i][j])) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    /**
     * 题目描述
     * 问题描述:
     *
     * 已知关系模式:S (SNO,SNAME) 学生关系。SNO 为学号，SNAME 为姓名
     *
     * C (CNO,CNAME,CTEACHER) 课程关系。CNO 为课程号，CNAME 为课程名，CTEACHER 为任课教师
     *
     * SC(SNO,CNO,SCGRADE) 选课关系。SCGRADE 为成绩
     *
     * 1.      找出没有选修过“李明”老师讲授课程的所有学生姓名
     * 2.      列出“1”号课成绩比“2”号课成绩高的所有学生的学号及其“1”号课和“2”号课的成绩
     */
    private  void as(){

    }
}
