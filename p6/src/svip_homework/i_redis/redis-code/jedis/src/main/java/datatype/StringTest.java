package datatype;

import util.JedisUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * iris
 */
public class StringTest {
//    private static String script = "" +
//            "local lockSet = redis.call('setnx', KEYS[1], ARGV[1])\n" +
//            "if lockSet == 1 then\n" +
//            "  redis.call('expire', KEYS[1], ARGV[2])\n" +
//            "end" +
//            "return lockSet";
    public static void main(String[] args) throws InterruptedException {

        //region 计算器
            //给帖子ID为0,1,2,3,4的浏览数+1
//            for (int i = 0; i <5 ; i++) {
//                JedisUtil.getJedisUtil().incr("count："+i);
//            }
//            //给帖子ID为1的浏览数+1
//            JedisUtil.getJedisUtil().incr("count：1");
//            //得到帖子1.2.3的浏览数
//            List<String> strList=JedisUtil.getJedisUtil().mget("count：1","count：2","count：3");
//            for (String s:strList) {
//                System.out.println(s);
//            }
        //endregion


        //region 分布式锁

        for (int i = 0; i < 2; i++) {
            new Thread(){
                public void run()  {
                    try {
                        //lua脚本分布式锁
                        //        Object a= JedisUtil.getJedisUtil().eval(script, Arrays.asList("lualock"), Arrays.asList("huihui","30"));
                        //        System.out.println(a.toString());
                        long exit=JedisUtil.getJedisUtil().setnx("lock","1");
                        if (exit==0)
                        {
                            System.out.println("被锁住了，不能执行");
                        }else
                        {
                            //为什么要给锁添加一个过期时间？
                            JedisUtil.getJedisUtil().expire("lock",30);
                            System.out.println("处理业务中");
                            Thread.sleep(3000);
                            //处理完业务逻辑删除锁
                       //     JedisUtil.getJedisUtil().del("lock");
                        }

                    }catch (Exception ex)
                    {

                    }
                };
            }.start();
            Thread.sleep(1000);
        }
        //endregion


    }


}
