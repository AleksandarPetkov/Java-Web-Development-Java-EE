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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {
    private static final String HTML_CREATE_CAT_FILE = "D:\\SoftUni\\Java-Web-Development-Java-EE\\fluffyduffymunchkincats\\src\\main\\resources\\view\\cat-create.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatCreateServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(this.htmlReader.readHtmlFile(HTML_CREATE_CAT_FILE));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = new Cat();
        cat.setName(req.getParameter("name"));
        cat.setBreed(req.getParameter("breed"));
        cat.setColor(req.getParameter("color"));
        cat.setAge(Integer.parseInt(req.getParameter("age")));

        if (req.getSession().getAttribute("cats") == null){
            req.getSession().setAttribute("cats", new HashMap<>());
        }


        ((Map<String, Cat>)req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);
        resp.sendRedirect(String.format("/cats/profile?catName=%s",cat.getName()));
    }
}
