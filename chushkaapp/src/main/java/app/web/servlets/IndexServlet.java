package app.web.servlets;

import app.domain.models.view.ProductViewModel;
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
import java.util.List;
import java.util.stream.Collectors;

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
        String indexHtmlContent = this.htmlReader
                .readHtmlFile(INDEX_HTML_PATH)
                .replace("[Item]", this.listItems());

        resp.getWriter().println(indexHtmlContent);
    }

    private String listItems(){
        StringBuilder sb = new StringBuilder();

        List<ProductViewModel> allProducts = this.productService.getAllProducts()
                .stream()
                .map(productModel -> this.modelMapper.map(productModel, ProductViewModel.class))
                .collect(Collectors.toList());

        allProducts.forEach(product -> {
            sb.append(String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>",
                    product.getName(),product.getName()))
                    .append(System.lineSeparator());
        });

        return sb.toString();
    }
}
