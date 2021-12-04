package datatype;

import util.JedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * iris
 */
public class SetTest {
    public static void main(String[] args) {
        //region 关注
//        //iris关注的人
//        JedisUtil.getJedisUtil().sadd("irisSub", "lily","lisa","zhangsan","lisi");
//        //lily关注的人
//        JedisUtil.getJedisUtil().sadd("lilySub", "lisa","wangwu","liuliu","liqi");
//        //iris取消关注zhangsan
//        JedisUtil.getJedisUtil().srem("irisSub","zhangsan");
//        //qignshan跟lily共同关注的人
//       Set<String> set=JedisUtil.getJedisUtil().sinter("irisSub","lilySub");
//       //qignshan可能认识的人，wangwu.liuliu，liqi都可能认识（第一个有，第二个没有）
//        Set<String>  set1= JedisUtil.getJedisUtil().sdiff("lilySub","irisSub");
//        System.out.println("iris跟lily共同关注的人："+set.toString());
//        System.out.println("iris可能认识的人："+set1.toString());
        //endregion

        //region 抽奖
        //我们公司有这么多人，有一个是灰灰（重复没用，会覆盖）
        JedisUtil.getJedisUtil().sadd("gupao","a","b","c","d","e","f","g","h","i","j","k","l",
                "iris");
        //年会抽奖，三等奖3个，二等奖2个，一等奖1个，被抽中的自动从奖池移除
        List<String> thirdPrizeList=new ArrayList<>();
        thirdPrizeList.add(JedisUtil.getJedisUtil().spop("gupao"));
        thirdPrizeList.add(JedisUtil.getJedisUtil().spop("gupao"));
        thirdPrizeList.add(JedisUtil.getJedisUtil().spop("gupao"));
        System.out.println("三等奖："+thirdPrizeList.toString());

        //二等奖
        List<String> secondPrizeList=new ArrayList<>();
        secondPrizeList.add(JedisUtil.getJedisUtil().spop("gupao"));
        secondPrizeList.add(JedisUtil.getJedisUtil().spop("gupao"));
        System.out.println("二等奖："+secondPrizeList.toString());

        //一等奖
        List<String> firstPrizeList=new ArrayList<>();
        firstPrizeList.add(JedisUtil.getJedisUtil().spop("gupao"));
        System.out.println("一等奖："+firstPrizeList.toString());
        //endregion
    }
}
