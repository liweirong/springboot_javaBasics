package com.iris.server.rpc;

import com.iris.rcp.util.MyRpcRequest;
import com.iris.server.rpc.annotion.Mediator;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 拦截反射调用
 */
@Slf4j
public class ProcessorHandler implements Runnable {

    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        log.info("进入拦截反射调用任务");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());) {
            MyRpcRequest rpcRequest = (MyRpcRequest) objectInputStream.readObject();

            log.info("拿到rpcRequest:{}", rpcRequest);
            // 去map中拿实例对象
            Mediator instance = Mediator.getInstance();
            Object processor = instance.processor(rpcRequest);
            objectOutputStream.writeObject(processor);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
