package org.myungkeun.crud_1.repository;

import org.myungkeun.crud_1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
