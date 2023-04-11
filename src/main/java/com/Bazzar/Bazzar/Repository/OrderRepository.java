package com.Bazzar.Bazzar.Repository;

import com.Bazzar.Bazzar.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordered,Integer> {
}
