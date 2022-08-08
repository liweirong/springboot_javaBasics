package svip_homework.d_rpc.protobuf;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.kone.pbdemo.protocol.FileOuterClass;
import netscape.javascript.JSObject;

import java.util.Arrays;

/**
 * @author iris
 * @date 2022/4/21
 */
public class Protobuf3 {

    /**
     * 注意点
     * 该文件的第一行指定使用 proto3 语法; 若是不写则使用 proto2.
     * 一个消息对应一个字段, 一个字段对应一个数据类型.
     */
    public static void main(String[] args) {
        com.kone.pbdemo.protocol.FileOuterClass.File file = com.kone.pbdemo.protocol.FileOuterClass.File.newBuilder()
                .setName("fileName")
                .setSize(200)
                .build();
        System.out.println(file);
        //4.序列化
        byte[] bytes = file.toByteArray();
        System.out.println("序列化后的数据:"+ Arrays.toString(bytes));
        System.out.println("序列化后的数据：,字节个数："+bytes.length);
        //5.反序列化
        try {
            FileOuterClass.File parseFrom = FileOuterClass.File.parseFrom(bytes);
            System.out.println(parseFrom);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
