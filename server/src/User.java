public class User {
    int id;//
    String username;
    int colour;


    public User(){
    	id = (int)(Math.random() * (90000000) + 10000000);
    	username="";
    }

    User(int _id, String _username, int _colour) {
        id = _id;
        username = _username;
        colour = _colour;
    }

    public int getId(){return id;}
    public void setUsername(String _username){username=_username;}
    public String getUsername(){return username;}
    public void setColor(int color){colour = color;}
    public int getColor(){return colour;}

}
