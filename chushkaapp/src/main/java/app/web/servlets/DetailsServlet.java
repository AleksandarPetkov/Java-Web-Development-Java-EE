package app.web.servlets;

import app.domain.models.ProductModel;
import app.domain.models.view.ProductsDetailsViewModel;
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

@WebServlet("/products/details")
public class DetailsServlet extends HttpServlet {

    private static final String PRODUCTS_DETAILS_HTML_FILE = "D:\\SoftUni\\Java-Web-Development-Java-EE\\chushkaapp\\src\\main\\resources\\views\\details.html";

    private final ProductService productService;

    private final HtmlReader htmlReader;

    private final ModelMapper modelMapper;

    @Inject
    public DetailsServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductModel productModel = this.productService.findByName(req.getQueryString().split("=")[1]);

        ProductsDetailsViewModel detailsViewModel = this.modelMapper.map(productModel, ProductsDetailsViewModel.class);

        String details = this.htmlReader.readHtmlFile(PRODUCTS_DETAILS_HTML_FILE)
                .replace("[ProductName]", detailsViewModel.getName())
                .replace("[productDesc]", detailsViewModel.getDescription())
                .replace("[productType]", detailsViewModel.getType());

        resp.getWriter().println(details);
    }
}
