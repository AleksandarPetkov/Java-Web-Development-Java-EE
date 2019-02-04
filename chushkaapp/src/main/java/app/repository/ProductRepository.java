package app.repository;

import app.domain.entities.Product;

public interface ProductRepository extends GenericRepository<Product, String> {

    Product findByName(String name);
}
