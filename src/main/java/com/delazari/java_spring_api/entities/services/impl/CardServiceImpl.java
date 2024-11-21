package com.delazari.java_spring_api.entities.services.impl;

import java.util.List;
import java.util.Optional;

import static com.delazari.java_spring_api.mappers.CardMapper.mapToCard;
import static com.delazari.java_spring_api.mappers.CardMapper.mapToCardDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delazari.java_spring_api.entities.Card;
import com.delazari.java_spring_api.entities.dtos.CardDTO;
import com.delazari.java_spring_api.entities.services.CardServices;
import com.delazari.java_spring_api.repositories.CardRepository;

@Service
public class CardServiceImpl implements CardServices{
	
	@Autowired
	private CardRepository cardRepository;

	@Override
	@Transactional
	public CardDTO create(CardDTO cardDto) {
		Card create = mapToCard(cardDto);
		Card created = cardRepository.save(create);
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