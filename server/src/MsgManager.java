import java.io.IOException;
import java.util.ArrayList;

public class MsgManager {
    public static ArrayList<ReceiveSocket> connectedSockets = new ArrayList<ReceiveSocket>();

    /**
     * add connected socket
     * @param connectedSocket
     */
    public static void addConnectedSocket(ReceiveSocket connectedSocket){
        if(!connectedSockets.contains(connectedSocket))
        connectedSockets.add(connectedSocket);
    }

    /**
     * remove disconnected socket
     * @param badSocket
     */
    public static void removeBadSocket(ReceiveSocket badSocket){
        if(connectedSockets.contains(badSocket))
        connectedSockets.remove(badSocket);
    }

    /**
     * broadcast message to all other sockets
     * @param msg msg need to be broadcast
     * @param broadcasters
     */
    public static void broadcast(String msg,ReceiveSocket broadcasters) throws IOException {
        msg = "Receive from other socket:"+msg;
        for(int i=0;i<connectedSockets.size();i++){
            if(connectedSockets.get(i)!=broadcasters){
                connectedSockets.get(i).sendMsgToClient(msg);
            }
        }
    }

    public static void broadcast(int x,int y,ReceiveSocket broadcasters) throws IOException {
        for(int i=0;i<connectedSockets.size();i++){
            if(connectedSockets.get(i)!=broadcasters){
                connectedSockets.get(i).sendMsgToClient(x,y);
            }
        }
    }
}
