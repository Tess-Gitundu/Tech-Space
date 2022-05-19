package Database;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class DB {
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/tech_spaces", "tess", "Njeri@Postgres");
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/samian", "samian", "root");
    // Development
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-3-231-82-226.compute-1.amazonaws.com:5432/debgbt3piakrs3?sslmode=require", "vtanfeaouixpjq", "da6a441059ed1cc4bd2b458733e476ed1c2a5b2143bbf6774e237a5037e53df9");


    public static void createTables(Connection conn) {
        try{
            conn.createQuery("CREATE TABLE IF NOT EXISTS users (\n" +
                    " id SERIAL PRIMARY KEY,\n" +
                    " userName VARCHAR UNIQUE,\n" +
                    " userLocation INT,\n" +
                    " language VARCHAR,\n" +
                    " available VARCHAR\n" +
                    ")").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }

        try{
            conn.createQuery("CREATE TABLE IF NOT EXISTS locations (\n" +
                    " id SERIAL PRIMARY KEY,\n" +
                    " location VARCHAR UNIQUE\n" +
                    ")").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }

        try{
            conn.createQuery("CREATE TABLE IF NOT EXISTS spaces (\n" +
                    " id SERIAL PRIMARY KEY,\n" +
                    " spacename VARCHAR UNIQUE,\n" +
                    " spacedetails VARCHAR,\n" +
                    " locationid INT,\n" +
                    " isfull VARCHAR\n" +
                    ")").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void dropTables(Connection conn) {
        try {
            conn.createQuery("DROP TABLE users").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
        try {
            conn.createQuery("DROP TABLE locations").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
        try {
            conn.createQuery("DROP TABLE spaces").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

}
