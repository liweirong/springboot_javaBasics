package svip_homework.design.factory;

/**
 * @author iris
 * @date 2021/10/11
 * 工厂方法模式 -
 * 适用于
 * 1 创建对象需要大量重复代码
 * 2 客户端不依赖于产品类别如何被创建，实现等细节
 * 3 一个类通过其他子类来指定创建哪些对象
 * <p>
 * 缺点
 * 1 类个数过多
 * 2 增加系统抽象性和理解难度
 */
public class FactoryMethodPattern {
    public static void main(String[] args) {
        ICarFactory iCarFactory = new OldCarFactory();
        ICar car = iCarFactory.create();
        car.make();

        new BatCarFactory().create().make();
    }
}

// 抽象工厂
interface ICarFactory {
    ICar create();
}

/**
 * 子工厂
 */
class OldCarFactory implements ICarFactory {
    @Override
    public ICar create() {
        return new OldCar();
    }
}

class BatCarFactory implements ICarFactory {
    @Override
    public ICar create() {
        return new BatCar();
    }
}
