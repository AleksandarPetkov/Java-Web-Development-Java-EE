package app.service;

import app.domain.entities.Product;
import app.domain.entities.Type;
import app.domain.models.ProductModel;
import app.repository.ProductRepository;
import app.util.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductModel productModel) {
        Product product = this.modelMapper.map(productModel, Product.class);
        product.setType(Type.valueOf(productModel.getType()));

        this.productRepository.save(product);
    }

    @Override
    public List<ProductModel> getAllProducts() {
       List<Product> products = this.productRepository.findAll();
       List<ProductModel> productModels = new ArrayList<>();
        for (Product product : products) {
            ProductModel current = this.modelMapper.map(product, ProductModel.class);
            current.setType(product.getType().name());
            productModels.add(current);
        }
        return productModels;
    }

    @Override
    public ProductModel findByName(String name) {
        Product product = this.productRepository.findByName(name);
        ProductModel productModel = this.modelMapper.map(product, ProductModel.class);
        productModel.setType(product.getType().name());

        return productModel;
    }
}
