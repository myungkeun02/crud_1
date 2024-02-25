package org.myungkeun.crud_1.controller;

import org.myungkeun.crud_1.exception.ResourceNotFoundException;
import org.myungkeun.crud_1.model.Product;
import org.myungkeun.crud_1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    // db 연결
    @Autowired
    private ProductRepository productRepository;

    // 전체 상품 정보 조회 api
    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    // 상품 id로 상품 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not exist with id: "+id));
        return ResponseEntity.ok(product);
    }
    // 상품정보를 생성하는 api
    @PostMapping
    public Product createProduct (@RequestBody Product product) {
        return productRepository.save(product);
    }
    // 상품 id로 상품 정보 삭제하는 api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        Product prudct = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not exist with id: "+id));
        productRepository.delete(prudct);
        return ResponseEntity.ok(id +"deleted");
    }
    // 상품 id로 상품 정보를 수정하는 api
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        Product updateProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id:" + id));
        updateProduct.setProductName(product.getProductName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setCategory(product.getCategory());
        productRepository.save(updateProduct);
        return ResponseEntity.ok(updateProduct);
    }
}
