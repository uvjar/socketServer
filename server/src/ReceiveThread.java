import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveThread implements Runnable{
    @Override
    public void run() {
        //Sever port
        int serverPort = 8888;
        try {
            System.out.println("SERVER START!!!!!!!!!！");
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while (true) {
                // blocking
                Socket sSocket = serverSocket.accept();
                ReceiveSocket newConnectedSocket = new ReceiveSocket(sSocket);
                MsgManager.addConnectedSocket(newConnectedSocket);
                newConnectedSocket.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
