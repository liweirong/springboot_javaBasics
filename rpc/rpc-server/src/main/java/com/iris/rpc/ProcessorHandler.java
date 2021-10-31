package com.iris.rpc;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProcessorHandler implements Runnable {

    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            // 通过反射去调用
//            Object object = invoke(rpcRequest);
            // 去map中拿实例对象
            Mediator instance = Mediator.getInstance();
            Object processor = instance.processor(rpcRequest);
            objectOutputStream.writeObject(processor);
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
