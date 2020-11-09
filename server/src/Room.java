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
    ArrayList<Integer> availableColors = new ArrayList<Integer>();    // todo: reconsider why we did this

    Room(int hostId) {
        roomId = (int) (Math.random() * (90000000) + 10000000);
        players = new ArrayList<>();
        players.add(hostId);
        this.hostId = hostId;
        game = new Game();
        gameOn = false;
        availableColors.add(1);
        availableColors.add(2);
        availableColors.add(3);
        availableColors.add(4);
        availableColors.add(5);
    }

    public int getRandomColor() {
        Random rand = new Random();
        int randomColor = players.get(rand.nextInt(availableColors.size()));
        availableColors.remove(new Integer(randomColor));
        return randomColor;
    }

    public void updateColors(int newColor) {
        availableColors.add(newColor);
    }

    public void addPlayer(int userId) {
        players.add(userId); // todo:
    }

    public void removeUser(int userId) {
        players.remove(userId);
    }

    public int getId() {
        return this.roomId;
    }

    public int getHostId() {
        return this.hostId;
    }

    public void printInfo() {
        System.out.println("Total number of users in room " + roomId + " is " + players.size());
        System.out.println("Room host is " + hostId);
    }

    public void startGame() {
        gameOn = true;
        game.resetBoard();
    }

    public void stopGame() {
        gameOn = false;
    }

    public boolean setPaint(int x, int y, int id) {
        return game.setPaint(x, y, id);
    }

    public boolean setRandomHost(User oldHost) {
        int oldHostId = oldHost.id;
        players.remove(new Integer(oldHostId));
        if (players.isEmpty()) {
            return false;
        }
        updateColors(oldHost.color);
        Random rand = new Random();
        this.hostId = players.get(rand.nextInt(players.size()));
        return true;
    }

    public void printAllPlayers() {
        for (Map.Entry<Integer, User> entry : idAndUser.entrySet())
            System.out.println("Key = " + entry.getKey() + ", Username = " + entry.getValue().username);
    }


}
