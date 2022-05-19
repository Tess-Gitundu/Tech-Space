package DAO;

import Database.DB;
import Interfaces.UserInterface;
import Models.Space;
import Models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserInterface {
    private final Sql2o sql2o;

    public UserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(User user) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO users (userName, userLocation, language, available) VALUES (:userName, :userLocation, :language, :available)";
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }
    }

    @Override
    public void update(int id, User user) {
        String sql = "UPDATE users SET userName=:userName, userLocation=:userLocation, language=:language, available=:available WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql).bind(user)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        try(Connection conn = DB.sql2o.open()) {
            List<User> dbUsers = conn.createQuery(sql).executeAndFetch(User.class);
            List<User> users = new ArrayList<>();
            for(int i=0; i<dbUsers.size(); i++) {
                User dbUser = dbUsers.get(i);
                User user = new User(dbUser.getUserName(), dbUser.getUserLocation(), dbUser.getLanguage(), dbUser.isAvailable());
                user.setId(dbUser.getId());
                users.add(user);
            }
            return users;
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id=:id";
        try(Connection conn = DB.sql2o.open()) {
            User dbUser = conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(User.class);
            User user = new User(dbUser.getUserName(), dbUser.getUserLocation(), dbUser.getLanguage(), dbUser.isAvailable());
            user.setId(dbUser.getId());
            return user;
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from users";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id=:id";
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery(sql).addParameter("id", id).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

}
