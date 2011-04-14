package controllers;

import play.*;
import play.mvc.*;

/**
 * 
 * @author garbagetown
 */
public class Application extends Controller {

    /**
     * 
     */
    public static void index() {

        String action = "index";

        render(action);
    }

    /**
     * 
     * @param version
     * @throws Exception
     */
    public static void documentation(String version) throws Exception {
        if (version == null) {
            version = Play.configuration.getProperty("version.latest");
        }
        Documentation.page(version, "home");
    }

    /**
     * 
     * @param action
     */
    public static void code(String action) {
        render(action);
    }
    
    /**
     * 
     * @param action
     */
    public static void about(String action) {
        
        render(action);
    }

}