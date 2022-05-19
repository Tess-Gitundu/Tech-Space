package DAO;

import Database.DB;
import Interfaces.SpaceInterface;
import Models.Space;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class SpaceDao implements SpaceInterface {
    private final Sql2o sql2o;

    public SpaceDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Space space) {
        try(Connection conn = DB.sql2o.open()) {
            int id = (int) conn.createQuery("INSERT INTO spaces (spacename, spacedetails, locationid, isfull) VALUES (:spaceName, :spaceDetails, :locationId, :isFull)", true).bind(space).executeUpdate().getKey();
            space.setId(id);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, Space space) {
        try(Connection conn = DB.sql2o.open()) {
            space.setId(id);
            conn.createQuery("UPDATE spaces SET spacename=:spaceName, spacedetails=:spaceDetails, locationid=:locationId, isfull=:isFull WHERE id=:id").addParameter("id", id).bind(space).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Space> getAll() {
        try(Connection conn = DB.sql2o.open()) {
            return conn.createQuery("SELECT * FROM spaces").executeAndFetch(Space.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Space findById(int id) {
        try(Connection conn = DB.sql2o.open()) {
            return conn.createQuery("SELECT * FROM spaces WHERE id=:id").addParameter("id", id).executeAndFetchFirst(Space.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Space> findFreeSpace() {
        try(Connection conn = DB.sql2o.open()) {
            return conn.createQuery("SELECT * FROM spaces WHERE isfull='false'").executeAndFetch(Space.class);
        } catch (Sql2oException ex) {
            throw new Sql2oException(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("DELETE FROM spaces WHERE id=:id").addParameter("id", id).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void clearAll() {
        try(Connection conn = DB.sql2o.open()){
            conn.createQuery("DELETE FROM spaces").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }
}
