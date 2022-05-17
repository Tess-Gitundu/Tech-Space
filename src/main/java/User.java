import java.util.List;
import java.util.Objects;

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return available == user.available && id == user.id && Objects.equals(userName, user.userName) && Objects.equals(userLocation, user.userLocation) && Objects.equals(language, user.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userLocation, language, available, id);
    }

    @Override
    public void add() {

    }

    @Override
    public void update() {

    }

    public static List<User> getAll() {
        return null;
    }

    public static User findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }


}
