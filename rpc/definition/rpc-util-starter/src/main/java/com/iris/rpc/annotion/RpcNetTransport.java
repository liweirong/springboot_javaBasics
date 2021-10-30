package com.iris.rpc.annotion;

import com.iris.rpc.util.MyRpcRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
@NoArgsConstructor
public class RpcNetTransport {

    private String host;
    private int port;

    /**
     * socket发送信息
     *
     * @param rpcRequest
     * @return
     */
    public Object send(MyRpcRequest rpcRequest) {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            return null;
        }
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            return objectInputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
