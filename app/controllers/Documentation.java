package controllers;

import java.io.*;
import java.util.*;

import play.*;
import play.libs.*;
import play.mvc.*;
import util.*;

/**
 * 
 * @author garbagetown
 */
public class Documentation extends Controller {

    private static List<String> versions;

    private static String latestVersion;

    static {
        versions = new ArrayList<String>();
        String[] dirNames = new File(Play.applicationPath, "documentation/").list();
        for (String name : dirNames) {
            if (name.equals("modules")) {
                continue;
            }
            versions.add(name);
        }
        Collections.sort(versions);
        Collections.reverse(versions);
        latestVersion = Play.configuration.getProperty("version.latest");
    }

    /**
     * 
     * @param version
     * @param id
     * @throws Exception
     */
    public static void page(String version, String id) throws Exception {

        List<String> versions = Documentation.versions;

        String action = "documentation";
        
        File page = new File(
                Play.applicationPath,
                "documentation/" + version + "/manual/" + id + ".textile");

        if (!page.exists()) {
            if (!version.equals(latestVersion)) {
                page(latestVersion, id);
            }
            notFound(page.getPath());
        }

        String textile = IO.readContentAsString(page);
        String html = Textile.toHTML(textile);
        String title = getTitle(textile);

        render(action, versions, version, id, html, title);
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