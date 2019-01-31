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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet("/cats/all")
public class CatAllServlet extends HttpServlet {
    private final String ALL_CATS_HTML_FILE = "D:\\SoftUni\\Java-Web-Development-Java-EE\\fluffyduffymunchkincats\\src\\main\\resources\\view\\all-cats.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatAllServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String htmlFile = this.htmlReader.readHtmlFile(ALL_CATS_HTML_FILE);

        if (req.getSession().getAttribute("cats") == null){
            htmlFile = htmlFile.replace("[AllCats]", String.format("The are no cats! <a href=\"/cats/create\" >Create Cat</a>"));

        } else {
            StringBuilder sb = new StringBuilder();

            ((Map<String,Cat>)req.getSession().getAttribute("cats")).values().stream()
                    .forEach(cat -> sb.append(String.format("<a href=\"/cats/profile?catName=%s\">%s</a><br/>",
                            cat.getName(),cat.getName())).append(System.lineSeparator()));

            htmlFile = htmlFile.replace("[AllCats]", sb.toString());

        }

       resp.getWriter().println(htmlFile);
    }
}
