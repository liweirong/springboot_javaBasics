package svip_homework.a_design.prototype;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LIWEIRONG172
 * @version 1.0
 * @date 2021-10-14
 * @describtion 深克隆的写法一般用序列化
 */
public class DeepCloneDemo {
    public static void main(String[] args) {
        Building1 build1 = new Building1();
        build1.setBuildName("5栋");
        Room1 room1 = new Room1();
        room1.setRoomName("1层101");
        room1.setRoomNum("510101");
        ArrayList<Room1> roomList = new ArrayList<>();
        roomList.add(room1);
        build1.setRoomList(roomList);
        System.out.println("本体" + build1);

        Building1 cloneBuilding = build1.deepClone();
        cloneBuilding.getRoomList().get(0).setRoomName("克隆1层101");
        System.out.println("克隆体" + cloneBuilding);
        System.out.println("本体" + build1);
    }
}

@Data
class Building1 implements Cloneable {
    private final String test = "final";
    private String buildName;
    private List<Room1> roomList;


    protected Building1 deepClone() {
        return JSONObject.parseObject(JSONObject.toJSONString(this), Building1.class);
    }
}

@Data
class Room1 {
    private String roomName;
    private String roomNum;
}