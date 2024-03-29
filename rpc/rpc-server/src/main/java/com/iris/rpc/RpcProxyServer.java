package com.iris.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(Object service,int port){
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);

            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
