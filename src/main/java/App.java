import static spark.Spark.*;

import DAO.LocationDao;
import DAO.SpaceDao;
import DAO.UserDao;
import Database.DB;
import Models.Space;
import Models.User;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.text.Collator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class App {
    private static Connection conn = DB.sql2o.open();
    private static UserDao userDao = new UserDao(DB.sql2o);
    private static LocationDao locationDao = new LocationDao(DB.sql2o);
    private static SpaceDao spaceDao = new SpaceDao(DB.sql2o);

    private static User currentUser;

    public static void main(String[] args) {
        DB.createTables(DB.sql2o.open());
        staticFileLocation("public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());

        get("spaces", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            User user = userDao.findById(currentUser.getId());
            List<Space> spaces = spaceDao.findFreeSpace(currentUser.getUserLocation());
            model.put("spaces", spaces);
            return new ModelAndView(model, "spaces.html");
        }, new HandlebarsTemplateEngine());

        get("/join", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("locations", locationDao.getAll());
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
            User user = new User(username, Integer.parseInt(location), language, available);
            userDao.save(user);
            currentUser = user;

            response.redirect("spaces");
            return null;
        });

    }
}
