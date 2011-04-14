package util;

import jj.play.org.eclipse.mylyn.wikitext.core.parser.*;
import jj.play.org.eclipse.mylyn.wikitext.textile.core.*;

/**
 * 
 * @author garbagetown
 */
public class Textile {

    /**
     * 
     * @param textile
     * @return
     */
    public static String toHTML(String textile) {
        String html = new MarkupParser(new TextileLanguage()).parseToHtml(textile);
        html = html.substring(html.indexOf("<body>") + 6, html.lastIndexOf("</body>"));
        return html;
    }
}
