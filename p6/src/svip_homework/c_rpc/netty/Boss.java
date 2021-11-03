package svip_homework.c_rpc.netty;

import java.nio.channels.ServerSocketChannel;

public interface Boss {

    /**
     * 加入一个新的serverSocketChannel
     *
     * @param serverSocketChannel
     */
    public void registerAcceptChannelTask(ServerSocketChannel serverSocketChannel);
}
