import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager {
    public static ArrayList<Room> allRooms = new ArrayList<Room>();
    //public static HashMap<Integer,Room> allRooms = new HashMap<Integer,Room>();


    public static void addRoom(Room r){
        if(!allRooms.contains(r))
            allRooms.add(r);
    }
    public static int getSize(){return allRooms.size();}
    public static Room getRandomRoom(){
        int tep= (int)(Math.random() * RoomManager.getSize());
        return allRooms.get(tep);
    }
    public static Room getRoomAtIndex(int idx){
        if(idx<allRooms.size()) return allRooms.get(idx);
        return null;
    }
    public static void removeRoom(Room r){
        if(allRooms.contains(r))
            allRooms.remove(r);
    }
    public static void printAllRoomInfo(){
        System.out.println("Total number of rooms: "+allRooms.size());
        for(Room r:allRooms){
            System.out.println("room "+ r.getId());
            r.printInfo();
        }
    }


}
