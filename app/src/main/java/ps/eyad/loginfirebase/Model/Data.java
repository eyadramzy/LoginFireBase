package ps.eyad.loginfirebase.Model;

public
class Data {
    private int id ;
    private String username ;
    private String time ;
    private int img , img_user ;

    public Data(int id, String username, String time, int img, int img_user) {
        this.id = id;
        this.username = username;
        this.time = time;
        this.img = img;
        this.img_user = img_user;
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg_user() {
        return img_user;
    }

    public void setImg_user(int img_user) {
        this.img_user = img_user;
    }
}
