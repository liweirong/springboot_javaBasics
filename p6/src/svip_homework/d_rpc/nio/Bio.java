package svip_homework.d_rpc.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author iris
 * @date 2022/4/18
 */
public class Bio {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        Socket clientSocket = socket.accept();

        OutputStream outputStream = clientSocket.getOutputStream();//发送信息
        InputStream inputStream= clientSocket.getInputStream(); //接收信息
    }
}
