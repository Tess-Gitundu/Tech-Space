import java.util.Objects;

public class Location implements Tech{

    private String location;

    public Location(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location1 = (Location) o;
        return Objects.equals(location, location1.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
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
