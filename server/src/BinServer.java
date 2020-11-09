public class BinServer {
    public static void main(String []args){
        new Thread(new ReceiveThread()).start();
    }
}