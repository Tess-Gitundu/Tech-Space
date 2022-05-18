package DAO;

import Database.DB;
import Interfaces.LocationInterface;
import Models.Location;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class LocationDao implements LocationInterface {
    private final Sql2o sql2o;

    public LocationDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Location location) {
        try(Connection conn = DB.sql2o.open()){
            int id = (int) conn.createQuery("INSERT INTO locations (location) VALUES (:location)", true).bind(location).executeUpdate().getKey();
            location.setId(id);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Location> getAll() {
        try(Connection conn = DB.sql2o.open()) {
            return conn.createQuery("SELECT * FROM locations").executeAndFetch(Location.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, Location location) {
        try(Connection conn = DB.sql2o.open()) {
            location.setId(id);
            conn.createQuery("UPDATE locations SET location=:location WHERE id=:id").addParameter("id", id).bind(location).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Location findById(int id) {
        try(Connection conn = DB.sql2o.open()) {
            return conn.createQuery("SELECT * FROM locations WHERE id=:id").addParameter("id", id).executeAndFetchFirst(Location.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("DELETE FROM locations WHERE id=:id").addParameter("id", id).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void clearAll() {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("DELETE FROM locations").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }
}
