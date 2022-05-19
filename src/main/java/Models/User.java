package Models;

import DAO.LocationDao;
import Database.DB;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class User {

    private String userName;
    private int userLocation;
    private String language;
    private boolean available;
    private int id;

    private Location location;

    public User(String userName, int userLocation, String language, boolean available) {
        this.userName = userName;
        this.userLocation = userLocation;
        this.language = language;
        this.available = available;
        setLocation();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(int userLocation) {
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


    public void setLocation() {
        LocationDao locationDao = new LocationDao(DB.sql2o);
        this.location = locationDao.findById(this.userLocation);;
    }

    public Location getLocation() {
        return location;
    }
}
