package svip_homework.design.factory;

/**
 * @author iris
 * @date 2021/10/11
 * 简单工厂
 */
public class SimpleFactory {
    public static void main(String[] args) {
        ICar bat = Factory.getObjByFactory(BatCar.class);
        ICar old = Factory.getObjByFactory(OldCar.class);
        bat.make();
        old.make();
    }
}

class Factory {
    public static ICar getObjByFactory(Class<? extends ICar> clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}

interface ICar {
    /**
     * 造车
     */
    void make();
}

class OldCar implements ICar {

    /**
     * 造车
     */
    @Override
    public void make() {
        System.out.println("制造一辆老爷车");
    }

}

class BatCar implements ICar {

    /**
     * 造车
     */
    @Override
    public void make() {
        System.out.println("制造一辆蝙蝠车");
    }
}