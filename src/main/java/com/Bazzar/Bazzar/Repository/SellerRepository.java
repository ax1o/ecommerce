package com.Bazzar.Bazzar.Repository;

import com.Bazzar.Bazzar.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Seller findByPanNo(String panNo);

}
