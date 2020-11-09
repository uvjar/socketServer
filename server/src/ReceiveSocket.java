import java.io.*;
import java.net.Socket;

public class ReceiveSocket extends Thread {

    private Socket sSocket;
    private OutputStream socketOutStream;
    private DataOutputStream dout;
    private User user;
    private int roomIndex=-1;

    public ReceiveSocket(Socket sSocket) throws IOException {
        this.sSocket = sSocket;
        this.socketOutStream = sSocket.getOutputStream();
        this.dout = new DataOutputStream(socketOutStream);
        this.user = new User();
    }

    public synchronized void sendMsgToClient(String msg) throws IOException {
        this.socketOutStream.write(msg.getBytes());
        System.out.println(this.getName() + "Thread received and return to client:" + msg);

    }
    public synchronized void sendMsgToClient(int x,int y) throws IOException {
        dout.writeInt(x);dout.writeInt(y);
        System.out.println(this.getName() + "Thread received and return to client:" + x+" "+y);
        dout.flush();
    }

    /** Every time a new sScoket is received, start a new thread*/
    @Override
    public void run() {
        try {
            System.out.println(this.getName() + "Thread Start---------------------------");
            InputStream inStream = sSocket.getInputStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(inStream));
            OutputStream outStream = sSocket.getOutputStream();
            System.out.println(this.getName() + "Thread start reading stream---------------------------");
            // byte[] inStreamBytes = new byte[1024];
            // int len;
//             while ((len = inStream.read(inStreamBytes)) > 0) {
//                 String strReceive = new String(inStreamBytes, 0, len);
// //                MsgManager.broadcast(strReceive,this.sSocket);
//                 // outStream.write(strReceive.getBytes());
// //                sendMsgToClient("Client send" + strReceive);
// //                MsgManager.broadcast(strReceive, this);
//                 System.out.println("Client send command " + strReceive);
//                 if(strReceive.equals("LOGIN")){
//                     this.socketOutStream.write(Integer.toString(user.getId()).getBytes());
//                     System.out.println(this.getName() + " user id:" + Integer.toString(user.getId()));
//                 }
//             }

            int command;
            while(true){
                command = dis.readInt();
                System.out.println(this.getName()+" send command " + command);
                switch(command){
                    case 0:
                        System.out.println("Receive command: LOGIN" );
                        this.socketOutStream.write(Integer.toString(user.getId()).getBytes());
                        System.out.println(this.getName() + " user id:" + Integer.toString(user.getId()));
                        break;
                    case 1:
                        System.out.println("Receive command: SET NAME" );
                        String name = dis.readUTF();
                        user.setUsername(name);
                        System.out.println("set user name to "+name);
                        break;
                    case 2:
                        System.out.println("Receive command: CREATE ROOM" );
                        Room newRoom = new Room(user.getId());
                        RoomManager.addRoom(newRoom);
                        roomIndex = RoomManager.getSize()-1;
                        this.socketOutStream.write(Integer.toString(newRoom.getId()).getBytes());
                        System.out.println("create room "+newRoom.getId() );
                        break;
                    case 3:
                        System.out.println("Receive command: JOIN RANDOM ROOM" );
                        int tep= (int)(Math.random() * RoomManager.getSize());
                        roomIndex=tep;
                        Room r = RoomManager.getRoomAtIndex(tep);
                        if(r!=null) {
                            r.addPlayer(user.getId());
                            this.socketOutStream.write(Integer.toString(r.getId()).getBytes());}
                        break;
                    case 4:
                        System.out.println("Receive command: START GAME" );
                        if(roomIndex>=0){
                            r=RoomManager.getRoomAtIndex(roomIndex);
                            if(r!=null &&user.getId()==r.getHostId()){
                                r.startGame();
                                String msg="Game Start";
                                MsgManager.broadcast(msg,this);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Receive command: SEND COORDINATE" );
                        int x = dis.readInt(); int y = dis.readInt();
                        System.out.println(this.getName()+" "+ x+" "+y);
                        if(roomIndex>=0){
                            r=RoomManager.getRoomAtIndex(roomIndex);
                            if(r!=null &&user.getId()==r.getHostId()){
                                r.setPaint(x,y,user.getId());
                                MsgManager.broadcast(x,y,this);
                            }
                        }
                        break;
                    case 10086:
                        System.out.println("DEBUG:");
                        RoomManager.printAllRoomInfo();
                        break;
                    default:
                }
            }
            //MsgManager.removeBadSocket(this);

        } catch (IOException e) {
            MsgManager.removeBadSocket(this);
            e.printStackTrace();
        }
        System.out.println(this.getName() + "run END!!!!!!!!!!!!");

    }


}
