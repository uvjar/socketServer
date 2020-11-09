import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendThread implements Runnable {
    @Override
    public void run() {
        try {
            int msgNumber = 10000;
            int serverPort = 8888;
            String inetAddress = "127.0.0.1";
            Socket socket = new Socket(inetAddress, serverPort);
            new ReceiveThread(socket).start();
            OutputStream outStream = socket.getOutputStream();
            DataOutputStream dout = new DataOutputStream(outStream);

            for (int i = 0; i < msgNumber; i++) {
                Scanner console = new Scanner(System.in);
                System.out.print("Enter command: \n" );
                int command = console.nextInt();
                String sendMsg;
                dout.writeInt(command);
                //sendMsg = "LOGIN";outStream.write(sendMsg.getBytes());
                switch (command) {
                    case 1:
                        String name = "NO NAME";
                        dout.writeUTF(name);
                        break;
                    case 5:
                        int x=console.nextInt();
                        dout.writeInt(x);
                        int y=console.nextInt();
                        dout.writeInt(y);
                        break;
                    default:
                }
                dout.flush();
            }
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
