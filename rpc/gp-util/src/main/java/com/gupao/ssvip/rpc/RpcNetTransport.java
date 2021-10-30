package com.gupao.ssvip.rpc;

import com.gupao.ssvip.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {

    private String host;
    private int port;

    public RpcNetTransport(){

    }

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(RpcRequest rpcRequest) {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return  objectInputStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

        }

        return null;
    }
}
