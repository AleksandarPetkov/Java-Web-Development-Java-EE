package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {

    private static final String CAT_PROFILE_HTML_FILE = "D:\\SoftUni\\Java-Web-Development-Java-EE\\fluffyduffymunchkincats\\src\\main\\resources\\view\\cat-profile.html";

    private static final String NON_EXIST_CAT_FILE = "D:\\SoftUni\\Java-Web-Development-Java-EE\\fluffyduffymunchkincats\\src\\main\\resources\\view\\non-exist.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats"))
                .get(req.getQueryString().split("=")[1]);

        String htmlFIle;
        if (cat == null) {
            htmlFIle = this.htmlReader.readHtmlFile(NON_EXIST_CAT_FILE)
                    .replace("{{catName}}", req.getQueryString().split("=")[1]);

            resp.getWriter().println(htmlFIle);

        } else {
            htmlFIle = this.htmlReader.readHtmlFile(CAT_PROFILE_HTML_FILE)
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catAge}}", Integer.toString(cat.getAge()));

            resp.getWriter().println(htmlFIle);
        }
    }
}
