package com.Bazzar.Bazzar.Repository;

import com.Bazzar.Bazzar.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRespository extends JpaRepository<Card,Integer> {
}
