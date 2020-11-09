import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Room {

    Map<Integer, User> idAndUser = new HashMap<>();

    int hostId;
    int roomId;
    ArrayList<Integer> players;
    Game game;
    boolean gameOn;


    Room(int hostId) {
        roomId= (int)(Math.random() * (90000000) + 10000000);
        players= new ArrayList<>();
        players.add(hostId);
        this.hostId=hostId;
        game = new Game();
        gameOn=false;
    }

    public void addPlayer(int userId) {
        players.add(userId); // todo:
    }

    public void removeUser(int userId) {
        players.remove(userId);
    }
    public int getId(){ return this.roomId;}
    public int getHostId(){return this.hostId;}
    public void printInfo(){
        System.out.println("Total number of users in room "+roomId+" is "+players.size());
        System.out.println("Room host is " + hostId);
    }

    public void startGame(){
        gameOn=true;game.resetBoard();
    }

    public void setPaint(int x,int y,int id){
        game.setPaint(x,y,id);
    }



    public void setRandomHost(int oldHost){
        Random rand = new Random();
        players.remove(oldHost);
        this.hostId = players.get(rand.nextInt(players.size()));
    }



    public void printAllPlayers(){
        for (Map.Entry<Integer, User> entry : idAndUser.entrySet())
            System.out.println("Key = " + entry.getKey() + ", Username = " + entry.getValue().username);
    }



}
