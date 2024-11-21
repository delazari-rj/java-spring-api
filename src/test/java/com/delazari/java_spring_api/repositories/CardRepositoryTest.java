package com.delazari.java_spring_api.repositories;

import static com.delazari.java_spring_api.communs.CardConstants.INVALID_CARD_EMPTY_NAME_FIELD;
import static com.delazari.java_spring_api.communs.CardConstants.INVALID_CARD_NULL;
import static com.delazari.java_spring_api.communs.CardConstants.VALID_CARD_ID_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import com.delazari.java_spring_api.entities.Card;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest
public class CardRepositoryTest {
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void createCard_ValidData_ReturnCard() {
		Card card = cardRepository.save(VALID_CARD_ID_NULL);
		
		Card sut = testEntityManager.find(Card.class, card.getId());
		
		assertThat(sut).isNotNull();
		assertThat(sut.getName()).isEqualTo(card.getName());
		assertThat(sut.getDescription()).isEqualTo(card.getDescription());
	}
	
	@Test
	public void createCard_WithTheSameName_ReturnDataIntegrityViolationException() {
		// TODO Refecture this test to use testEntityManager
		cardRepository.save(VALID_CARD_ID_NULL);		
		assertThatThrownBy(() -> cardRepository.save(VALID_CARD_ID_NULL)).isInstanceOf(DataIntegrityViolationException.class);
	}
	
	@Test
	public void createCard_CardNull_ReturnConstraintViolationException() {
		assertThatThrownBy(() -> cardRepository.save(INVALID_CARD_NULL)).isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	public void createCard_InvalidData_ReturnException() {
		assertThatThrownBy(() -> cardRepository.save(INVALID_CARD_EMPTY_NAME_FIELD)).isInstanceOf(ConstraintViolationException.class);;
	}
}
