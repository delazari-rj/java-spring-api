package com.delazari.java_spring_api.entities.services.impl;

import static com.delazari.java_spring_api.mappers.CardMapper.mapToCard;
import static com.delazari.java_spring_api.mappers.CardMapper.mapToCardDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delazari.java_spring_api.entities.Card;
import com.delazari.java_spring_api.entities.dtos.CardDTO;
import com.delazari.java_spring_api.entities.services.CardServices;
import com.delazari.java_spring_api.repositories.CardRepository;

import jakarta.validation.ConstraintViolationException;

@Service
public class CardServiceImpl implements CardServices{
	
	@Autowired
	private CardRepository cardRepository;

	@Override
	@Transactional
	public CardDTO create(CardDTO cardDto) {
		Card created;
		try {
			created = cardRepository.save(mapToCard(cardDto));
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("There is a card with the same name already registered.");
		} catch (ConstraintViolationException e) {
			throw new IllegalArgumentException("The value of the Name field is invalid.");
		} 
		return mapToCardDTO(created);
	}

	@Override
	@Transactional(readOnly = true)
	public CardDTO getById(Long id) {
		Optional<Card> recovered = cardRepository.findById(id);
		return mapToCardDTO(recovered.get()); 
	}

	@Override
	@Transactional(readOnly = true)
	public List<CardDTO> getAll() {
		List<Card> recovered = cardRepository.findAll();
		return recovered.stream().map(CardDTO::new).toList();
	}

	@Override
	@Transactional
	public CardDTO update(CardDTO cardDto) {
		Card update = mapToCard(cardDto);
		Card updated = cardRepository.save(update);
		return mapToCardDTO(updated);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		cardRepository.deleteById(id);
	}
}
