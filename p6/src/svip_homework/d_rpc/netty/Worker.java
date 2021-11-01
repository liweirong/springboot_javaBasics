package svip_homework.d_rpc.netty;

import java.nio.channels.SocketChannel;

public interface Worker {

    /**
     * 加入一个新的socketChannel
     *
     * @param socketChannel
     */
    void registerNewChannelTask(SocketChannel socketChannel);
}
