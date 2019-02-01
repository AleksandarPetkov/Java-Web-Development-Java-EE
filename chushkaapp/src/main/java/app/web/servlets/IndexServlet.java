package app.web.servlets;

import app.service.ProductService;
import app.util.HtmlReader;
import app.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private static final String INDEX_HTML_PATH = "D:\\SoftUni\\Java-Web-Development-Java-EE\\chushkaapp\\src\\main\\resources\\views\\index.html";

    private final ProductService productService;

    private final HtmlReader htmlReader;

    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String indexHtmlContent = this.htmlReader.readHtmlFile(INDEX_HTML_PATH);
        resp.getWriter().println(indexHtmlContent);
    }
}
