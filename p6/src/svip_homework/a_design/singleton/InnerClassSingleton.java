package svip_homework.a_design.singleton;

/**
 * @author iris
 * @date 2021/10/12
 */
public class InnerClassSingleton {
    public static void main(String[] args) {
        System.out.println(SingleTon.a);
        System.out.println(SingleTon.getInstance());
    }
}


class SingleTon {
    static int a = 1;
    private SingleTon() {
    }

    private static class SingleTonInner {
        private static SingleTon INSTANCE = new SingleTon();
    }

    public static SingleTon getInstance() {
        return SingleTonInner.INSTANCE;
    }
}
