public class BinClient {
    public static void main(String []args){
        new Thread(new SendThread()).start();
    }
}