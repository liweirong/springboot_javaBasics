package svip_homework.a_design.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LIWEIRONG172
 * @version 1.0
 * @date 2021-10-14
 * @describtion 建造者模式   目的是为了分离对象的属性与创建过程
 *
 * 优点 ：封装性好，创建与使用分离
 * 缺点 ：多了builder类
 * 适用于：较多零件的复杂产品的创建，由于需求变化，各个零件会变，但是组合方式却相对稳定
 */
public class Builder {

    public static void main(String[] args) {
        BuilderObject builderObject = BuilderObject.builder().id("id1").build();
        System.out.println(builderObject);


        StringBuilder stringBuilder = new StringBuilder("a");
        stringBuilder.append("b");
        // 开放构造步骤最后得到一个构造好的字符串
        System.out.println(stringBuilder);
    }
}

@lombok.Builder
@ToString
@Getter
@Setter
class BuilderObject {
    private String id;
    private String name;
}
