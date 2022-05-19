import static spark.Spark.*;

import DAO.LocationDao;
import DAO.SpaceDao;
import DAO.UserDao;
import Database.DB;
import Models.Location;
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

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        DB.createTables(DB.sql2o.open());
        staticFileLocation("public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());

        get("spaces", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Space> spaces = spaceDao.getAll();
            try{
                User user = userDao.findById(currentUser.getId());
                spaces = spaceDao.findFreeSpace(currentUser.getUserLocation());
            } catch (Exception e) {
                spaces = spaceDao.getAll();
            }
            model.put("spaces", spaces);
            model.put("titleSpaces", true);
            return new ModelAndView(model, "spaces.html");
        }, new HandlebarsTemplateEngine());

        get("/join", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("locations", locationDao.getAll());
            model.put("titleFind", true);
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

        get("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("users", userDao.getAll());
            model.put("titleUsers", true);
            return new ModelAndView(model, "users.html");
        }, new HandlebarsTemplateEngine());

        get("/reset", (request, response) -> {
            DB.dropTables(conn);
            DB.createTables(conn);
            response.redirect("/");
            return null;
        });

        get("/populatedb", (request, response) -> {
            // add locations
            Location nairobi = new Location("Nairobi");
            locationDao.add(nairobi);
            Location mombasa = new Location("Mombasa");
            locationDao.add(mombasa);
            Location kisumu = new Location("Kisumu");
            locationDao.add(kisumu);
            Location nakuru = new Location("Nakuru");
            locationDao.add(nakuru);

            // SPACES
                // Nairobi
            spaceDao.add(new Space("iHub coding space", "Tech space for open to all geeks", nairobi.getId(), false));
            spaceDao.add(new Space("UON tech centre", "Free coding space for young developers", nairobi.getId(), false));
            spaceDao.add(new Space("CityHub Tech centre", "Code code code", nairobi.getId(), false));
            spaceDao.add(new Space("Moringa coding space", "Cyber innovation center", nairobi.getId(), false));

                // Mombasa
            spaceDao.add(new Space("Swahili tech hub", "Always be coding", mombasa.getId(), false));
            spaceDao.add(new Space("Ziwani ICT center", "Every line counts", mombasa.getId(), false));

                // Kisumu
            spaceDao.add(new Space("Victoria Tech Hall", "Your code, our future", nairobi.getId(), false));
            spaceDao.add(new Space("Greenwood centre", "Coding for the next generation", nairobi.getId(), false));
            spaceDao.add(new Space("Code alive hub", "The place to be for all developers", nairobi.getId(), false));

                // Nakuru
            spaceDao.add(new Space("Computech Hub", "Where great minds meet.", nairobi.getId(), false));
            response.redirect("/");
            return null;
        });

    }
}
