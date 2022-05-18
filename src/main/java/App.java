import static spark.Spark.*;

import Database.DB;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;


public class App {
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
            response.redirect("spaces");
            return null;
        });

    }
}
