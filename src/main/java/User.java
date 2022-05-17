import org.sql2o.Connection;
import org.sql2o.Sql2oException;

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
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO users (userName, userLocation, language, available) VALUES (:userName, :userLocation, :language, :available)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("userName", this.userName)
                    .addParameter("userLocation", this.userLocation)
                    .addParameter("language", this.language)
                    .addParameter("available", this.available)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update(String userName, String userLocation, String language, boolean available) {
        String sql = "UPDATE users SET (userName, userLocation, language, available) = (SELECT :userName, userLocation, language, available FROM users) WHERE id=:id";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userName", userName)
                    .addParameter("userLocation", userLocation)
                    .addParameter("language", language)
                    .addParameter("available", available)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public static List<User> getAll() {
        String sql = "SELECT * FROM users";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    public static User findById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM users WHERE id=:id";
            User user = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
            return user;
        }
    }

}