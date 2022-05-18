import static spark.Spark.*;

import DAO.LocationDao;
import DAO.UserDao;
import Database.DB;
import Models.User;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.text.Collator;
import java.util.HashMap;
import java.util.Map;


public class App {
    private static Connection conn = DB.sql2o.open();
    private static UserDao userDao = new UserDao(DB.sql2o);
    private static LocationDao locationDao = new LocationDao(DB.sql2o);

    public static void main(String[] args) {
        DB.createTables(DB.sql2o.open());
        staticFileLocation("public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());

        get("spaces", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "spaces.html");
        }, new HandlebarsTemplateEngine());

        get("/join", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "signUp.html");
        }, new HandlebarsTemplateEngine());

        post("/add", (request, response) -> {
            String username = request.queryParams("username");
            String location = request.queryParams("location");
            String language = request.queryParams("language");
            String availability = request.queryParams("available");
            Boolean available = false;
            Collator myCollator = Collator.getInstance();
            try{
                if (myCollator.compare(availability, "true") == 0){
                    available = true;
                }
            } catch (Exception e){
                available = false;
            }
            User user = new User(username, location, language, available);
            userDao.save(user);
            response.redirect("spaces");
            return null;
        });

    }
}
