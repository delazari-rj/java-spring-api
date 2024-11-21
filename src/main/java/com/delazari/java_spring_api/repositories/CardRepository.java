package com.delazari.java_spring_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delazari.java_spring_api.entities.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
