public class User implements Tech{

    private String userName;
    private String userLocation;
    private int id;

    public User(String userName, String userLocation) {
        this.userName = userName;
        this.userLocation = userLocation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void add() {

    }

    @Override
    public void findById(int id) {

    }

    @Override
    public void update() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
