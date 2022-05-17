public class User implements Tech{

    private String userName;
    private String userLocation;
    private String language;
    private boolean available;
    private int id;

    public User(String userName, String userLocation, String language, boolean available) {
        this.userName = userName;
        this.userLocation = userLocation;
        this.language = language;
        this.available = available;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
