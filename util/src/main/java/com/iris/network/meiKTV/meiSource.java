package com.iris.network.meiKTV;

import com.iris.network.HttpClientUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author iris
 * @date 2021/6/3
 */
public class meiSource {
    // https://v.meiktv.com/video/100/1622266987991111/239410032_vvideo.mp4
    // https://v.meiktv.com/video/100/1622266987991111/239410041_vvideo.mp4
    // 你就不要想起我 简弘亦  https://v.meiktv.com/video/100/1622266987991111/239410044_vvideo.mp4
    // 那些你很冒险的梦 林俊杰https://v.meiktv.com/video/100/1617426030662067/238010007_vvideo.mp4

    //  1622266987991
    //  1617426030662 2021.4.3 13:00:30
    private static String domain = "https://v.meiktv.com/video/100/";
 // domain+ /开房时间戳+ 111（房间编码）/ 随机串 + 序号+_vvideo.mp4
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) throws Exception {
        String startDate = "2021-05-03";
        Date start = simpleDateFormat.parse(startDate);
        String url = domain +start+""+"/"+"_vvideo.mp4";

        String test ="https://v.meiktv.com/video/100/1622266987991111/2394100322_vvideo.mp4";
        String s = HttpClientUtils.get(test);
        System.out.println(s);
    }
}
