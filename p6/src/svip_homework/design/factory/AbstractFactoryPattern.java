package svip_homework.design.factory;

/**
 * @author iris
 * @date 2021/10/11
 * 创建一系列相关或相互依赖的接口
 */
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        System.out.println("开始生产小米电脑");
        ComputerFactory computerFactory = new XiaoMiFactory();
        computerFactory.createScreen().makeScreen();
        computerFactory.createKeyboard().makeKeyboard();

        System.out.println("开始生产华为电脑");
        ComputerFactory HUAWEIComputerFactory = new HUAWEIFactory();
        HUAWEIComputerFactory.createScreen().makeScreen();
        HUAWEIComputerFactory.createKeyboard().makeKeyboard();
    }
}

/**
 * 组装电脑
 */
abstract class ComputerFactory {
    abstract IScreen createScreen();

    abstract IKeyboard createKeyboard();
}

class HUAWEIFactory extends ComputerFactory {

    @Override
    IScreen createScreen() {
        return new HUAWEIScreen();
    }

    @Override
    IKeyboard createKeyboard() {
        return new HUAWEIKeyboard();
    }
}


class XiaoMiFactory extends ComputerFactory {

    @Override
    IScreen createScreen() {
        return new XiaoMiScreen();
    }

    @Override
    IKeyboard createKeyboard() {
        return new XiaoMiKeyboard();
    }
}

/**
 * 显示器
 */
interface IScreen {
    void makeScreen();
}

/**
 * 键盘
 */
interface IKeyboard {
    void makeKeyboard();
}

class HUAWEIScreen implements IScreen {

    @Override
    public void makeScreen() {
        System.out.println("生成一个华为显示屏");
    }
}

class HUAWEIKeyboard implements IKeyboard {

    @Override
    public void makeKeyboard() {
        System.out.println("生成一个华为键盘");
    }
}

class XiaoMiScreen implements IScreen {

    @Override
    public void makeScreen() {
        System.out.println("生成一个小米显示屏");
    }
}

class XiaoMiKeyboard implements IKeyboard {

    @Override
    public void makeKeyboard() {
        System.out.println("生成一个小米键盘");
    }
}