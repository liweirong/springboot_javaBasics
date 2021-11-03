package svip_homework.c_rpc.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Start {

    public static void main(String[] args) {
        // boss线程池
        Executor boss = Executors.newCachedThreadPool();

        Executor work = Executors.newCachedThreadPool();

        NioSelectorRunnablePool nioSelectorRunnablePool = new NioSelectorRunnablePool(boss, work);

        ServerBootStrap bootStrap = new ServerBootStrap(nioSelectorRunnablePool);
        bootStrap.bind(new InetSocketAddress(8080));
        System.out.println("start 8080");

    }
}
