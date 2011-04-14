package controllers;

import java.io.*;

import play.*;
import play.libs.*;
import play.mvc.*;
import util.*;

/**
 * 
 * @author garbagetown
 */
public class Documentation extends Controller {

    /**
     * 
     * @param version
     * @param id
     * @throws Exception
     */
    public static void page(String version, String id) throws Exception {

        String action = "documentation";
        
        String latest = Play.configuration.getProperty("version.latest");

        File page = new File(
                Play.applicationPath,
                "documentation/" + version + "/manual/" + id + ".textile");

        if (!page.exists()) {
            if (!version.equals(latest)) {
                page(latest, id);
            }
            notFound(page.getPath());
        }

        String textile = IO.readContentAsString(page);
        String html = Textile.toHTML(textile);
        String title = getTitle(textile);

        render(action, version, id, html, title);
    }

    /**
     * 
     * @param version
     * @param name
     */
    public static void image(String version, String name) {
        renderBinaryFile("documentation/" + version + "/images/" + name + ".png");
    }

    /**
     * 
     * @param version
     * @param name
     */
    public static void file(String version, String name) {
        renderBinaryFile("documentation/" + version + "/files/" + name);
    }
    
    /**
     * 
     * @param filepath
     */
    static void renderBinaryFile(String filepath) {
        File file = new File(Play.applicationPath, filepath);
        if (!file.exists()) {
            notFound(file.getPath());
        }
        renderBinary(file);
    }
    
    /**
     * 
     * @param textile
     * @return
     */
    static String getTitle(String textile) {
        if (textile.length() == 0) {
            return "";
        }
        return textile.split("\n")[0].substring(3).trim();
    }

}