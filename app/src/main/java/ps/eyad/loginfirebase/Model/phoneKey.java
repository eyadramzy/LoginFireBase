package ps.eyad.loginfirebase.Model;

public
class phoneKey {
    private int id ;
    private String key ;
    private String countryName ;
    private int flag ;

    public phoneKey(int id, String key, String countryName, int flag) {
        this.id = id;
        this.key = key;
        this.countryName = countryName;
        this.flag = flag;
    }

    public phoneKey() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
