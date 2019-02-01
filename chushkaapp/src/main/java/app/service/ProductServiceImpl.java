package app.service;

import app.domain.entities.Product;
import app.domain.entities.Type;
import app.domain.models.ProductModel;
import app.repository.ProductRepository;
import app.util.ModelMapper;

import javax.inject.Inject;

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
}
