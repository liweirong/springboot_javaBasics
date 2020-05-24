import java.util.ArrayList;
import java.util.List;


/**
 * @author iris
 * @date 2020/4/19
 */
public class 微爱 {
    public static void main(String[] args) {
        List<Stat> list = getListFromDB();
        System.out.println("-------------db_list------------");
        printList(list);
        List<Stat> result = getSortList(list);
        System.out.println("------------sort_result------------");
        printList(result);
    }


    /**
     * 按照日期进行合并
     * ！ 不要用map
     *
     * @param list
     * @return
     */
    private static List<Stat> getSortList(List<Stat> list) {
        List<Stat> result = new ArrayList<>(16);
        if (list == null || list.isEmpty()) {
            return result;
        }
        // 缓存
        Stat temp = new Stat();
        for (Stat t : list) {
            // 如果和缓存的date一样,则进行累加
            if (temp.getDate() != null && temp.getDate().equals(t.getDate())) {
                temp.setRegisterUserCount(temp.getRegisterUserCount() + t.getRegisterUserCount());
                temp.setActiveUserCount(temp.getActiveUserCount() + t.getActiveUserCount());
                // 切换date不同的数据，进行记录
            } else {
                temp = t;
                // 避免第一次进行记录
                if (temp.getDate() != null) {
                    result.add(temp);
                }
            }

        }
        return result;
    }

    /**
     * list已经排好序了 - 按照日期
     *
     * @return 测试假数据
     */
    private static List<Stat> getListFromDB() {
        List<Stat> list = new ArrayList<>();
        Stat s = new Stat();
        s.setActiveUserCount(1063);
        s.setDate("2020-04-19");
        s.setRegisterUserCount(97);
        Stat s1 = new Stat();
        s1.setActiveUserCount(2065);
        s1.setDate("2020-04-19");
        s1.setRegisterUserCount(65);
        Stat s2 = new Stat();
        s2.setActiveUserCount(1660);
        s2.setDate("2020-04-20");
        s2.setRegisterUserCount(72);
        list.add(s);
        list.add(s1);
        list.add(s2);
        return list;
    }

    private static void printList(List<Stat> result) {
        result.forEach(System.out::println);
    }

}

class Stat {
    private String date;// yyyy-MM-dd
    private int registerUserCount;
    private int activeUserCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRegisterUserCount() {
        return registerUserCount;
    }

    public void setRegisterUserCount(int registerUserCount) {
        this.registerUserCount = registerUserCount;
    }

    public int getActiveUserCount() {
        return activeUserCount;
    }

    public void setActiveUserCount(int activeUserCount) {
        this.activeUserCount = activeUserCount;
    }

    @Override
    public String toString() {
        return "date:" + date + " activeUserCount:" + activeUserCount + " registerUserCount:" + registerUserCount;
    }
}