package app.service;
import app.domain.models.ProductModel;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductModel productModel);

    List<ProductModel> getAllProducts();

    ProductModel findByName(String name);
}
