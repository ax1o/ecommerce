package com.Bazzar.Bazzar.Repository;

import com.Bazzar.Bazzar.Enum.Category;
import com.Bazzar.Bazzar.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product>findByCategory(Category category);

    @Query(value = "select * from product p Order by p.price Limit 5",nativeQuery = true)
    List<Product>findCheapestProduct();

}
