import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/tech_spaces", "tess", "Njeri@Postgres");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteUserQuery = "DELETE FROM users *;";
            String deleteLocationQuery = "DELETE FROM locations *;";
            con.createQuery(deleteUserQuery).executeUpdate();
            con.createQuery(deleteLocationQuery).executeUpdate();
        }
    }
}
