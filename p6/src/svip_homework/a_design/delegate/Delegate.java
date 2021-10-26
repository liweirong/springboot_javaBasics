package svip_homework.a_design.delegate;

/**
 * @author iris
 * @date 2021/10/13
 * <p>
 * 委派模式 - 行为型模式
 * 委派模式负责任务到的调用和分配,委派注重的是结果,最后委派给谁去执行了
 * spring中也有相关的应用,最熟悉的就是DispatcherServlet
 */
public class Delegate {
    public static void main(String[] args) {
        Leader leader = new Leader();
        leader.work("出差");
        leader.work("请假");
    }
}

interface IWork {
    void work(String cmd);
}

class Leader {
    public void work(String cmd) {
        if (cmd.equals("出差")) {
            new AWork().work(cmd);
            return;
        }
        new BWork().work(cmd);
    }

}


class AWork implements IWork {
    @Override
    public void work(String cmd) {
        System.out.println("A员工接收到指令[" + cmd + "]开始执行相关工作");
    }
}

class BWork implements IWork {
    @Override
    public void work(String cmd) {
        System.out.println("B员工接收到指令[" + cmd + "]开始执行相关工作");
    }
}

