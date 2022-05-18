package Database;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class DB {
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/tech_spaces", "tess", "Njeri@Postgres");
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/samian", "samian", "root");

    public static void createTables(Connection conn) {
        try{
            conn.createQuery("CREATE TABLE IF NOT EXISTS users2 (\n" +
                    " id SERIAL PRIMARY KEY,\n" +
                    " userName VARCHAR,\n" +
                    " userLocation VARCHAR,\n" +
                    " language VARCHAR,\n" +
                    " available VARCHAR\n" +
                    ")").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }

        try{
            conn.createQuery("CREATE TABLE IF NOT EXISTS locations (\n" +
                    " id SERIAL PRIMARY KEY,\n" +
                    " location VARCHAR\n" +
                    ")").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

}
