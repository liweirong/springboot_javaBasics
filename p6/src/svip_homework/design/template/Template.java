package svip_homework.design.template;

/**
 * @author LIWEIRONG172
 * @version 1.0
 * @date 2021-10-14
 * @describtion 模板方法模式 行为型
 * 模板方法模式 定义算法的框架，实现延迟在子类中
 *
 * 优点：将不变的东西放在父类，提高复用性，变化的东西放在子类，提高扩展性
 * 缺点：父类增加了新的抽象方法 子类全需要改
 *
 * <p>
 * 适用于：当完成一个操作有固定的流程时，通过抽象固定流程，子类继承实现流程细节
 *
 * 比如JdbcTemplate 封装了所有操作，执行sql的流程也比较固定 需要获取连接 执行语句 关闭连接 ，真正变的是执行语句的变化
 */
public class Template {
    public static void main(String[] args) {
        AbstractGame footballGame = new FootballGame();
        AbstractGame basketballGame = new BasketballGame();
        footballGame.play();
        basketballGame.play();
    }
}

abstract class AbstractGame {

    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    public void play() {
        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }

}

class FootballGame extends AbstractGame {

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}

class BasketballGame extends AbstractGame {

    @Override
    void endPlay() {
        System.out.println("Basketball Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Basketball Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Basketball Game Started. Enjoy the game!");
    }
}