package dao;

import models.Product;

import java.util.List;

public interface ProductDAO {

    // Thêm sản phẩm
    void addProduct(Product product);

    // Lấy danh sách sản phẩm
    List<Product> getAllProducts();

    // Cập nhật tồn kho sản phẩm
    boolean updateStock(int productId, int newQuantity);

    // Lấy sản phẩm theo ID
    Product getProductById(int productId);
}
