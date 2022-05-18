package DAO;

import Database.DB;
import Interfaces.UserInterface;
import Models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class UserDao implements UserInterface {
    private final Sql2o sql2o;

    public UserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(User user) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO users2 (userName, userLocation, language, available) VALUES (:userName, :userLocation, :language, :available)";
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }
    }

    @Override
    public void update(int id, User user) {
        String sql = "UPDATE users2 SET userName=:userName, userLocation=:userLocation, language=:language, available=:available WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql).bind(user)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users2";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    @Override
    public User findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM users2 WHERE id=:id";
            User user = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
            return user;
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from users2";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {

    }

}
