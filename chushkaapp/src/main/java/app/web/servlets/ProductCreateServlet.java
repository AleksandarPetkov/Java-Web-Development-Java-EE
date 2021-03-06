package app.web.servlets;

import app.domain.entities.Type;
import app.domain.models.ProductModel;
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

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    private static final String CREATE_PRODUCT_HTML_FILE = "D:\\SoftUni\\Java-Web-Development-Java-EE\\chushkaapp\\src\\main\\resources\\views\\create-products.html";

    private final ProductService productService;

    private final HtmlReader htmlReader;

    private final ModelMapper modelMapper;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         [TypeOptions]
        String createHtmlContent = this.htmlReader.readHtmlFile(CREATE_PRODUCT_HTML_FILE);
        createHtmlContent = createHtmlContent
                .replace(" [TypeOptions]", this.getTypeOptions());
        resp.getWriter().println(createHtmlContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductModel productModel = new ProductModel();
        productModel.setName(req.getParameter("name"));
        productModel.setDescription(req.getParameter("description"));
        productModel.setType(req.getParameter("type"));

        this.productService.saveProduct(productModel);
        resp.sendRedirect("/");
    }

    private String getTypeOptions(){
        StringBuilder sb = new StringBuilder();
        for (Type value : Type.values()) {
            sb.append(String.format("<option value=\"%s\">%s</option>", value.name(), value.name()));
        }
        return sb.toString();
    }
}
