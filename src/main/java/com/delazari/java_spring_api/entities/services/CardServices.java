package com.delazari.java_spring_api.entities.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delazari.java_spring_api.entities.dtos.CardDTO;

@Service
public interface CardServices {
	
	CardDTO create(CardDTO cardDto);
	CardDTO getById(Long id);
	List<CardDTO> getAll();
	CardDTO update(CardDTO cardDto);
	void deleteById(Long id);
}
