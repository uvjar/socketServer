public class User {
    int id;//
    String username;
    int color;


    public User(){
    	id = (int)(Math.random() * (90000000) + 10000000);
    	username="";
    }

    User(int _id, String _username, int _color) {
        id = _id;
        username = _username;
        color = _color;
    }

    public int getId(){return id;}
    public void setUsername(String _username){username=_username;}
    public String getUsername(){return username;}
    public void setColor(int color){color = color;}
    public int getColor(){return color;}
}
