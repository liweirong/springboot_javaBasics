package svip_homework.a_design.proxy;

/**
 * @author iris
 * @date 2021/10/12
 */
public class StaticProxy {
    public static void main(String[] args) {
        StaticProxyBean staticProxyBean = new StaticProxyBean();
        staticProxyBean.proxy(new AloneStudy());
        staticProxyBean.learn();
    }
}

class StaticProxyBean implements IStudy {
    private IStudy study;

    public void proxy(IStudy study) {
        this.study = study;
    }

    @Override
    public void learn() {
        before();
        study.learn();
        after();
    }

    private void before() {
        System.out.println("学习前 - 穿上一身华丽的燕尾服");
    }

    private void after() {
        System.out.println("学习后 - 洗上一个大澡愉快入睡");
    }


}

interface IStudy {
    void learn();
}

class AloneStudy implements IStudy {
    @Override
    public void learn() {
        System.out.println("独自一人学习");
    }
}