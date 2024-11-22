package com.delazari.java_spring_api.repositories;

import static com.delazari.java_spring_api.communs.CardConstants.INVALID_CARD_EMPTY_NAME_FIELD;
import static com.delazari.java_spring_api.communs.CardConstants.INVALID_CARD_NULL;
import static com.delazari.java_spring_api.communs.CardConstants.INVALID_CARD_LONG_NAME_FIELD;
import static com.delazari.java_spring_api.communs.CardConstants.VALID_CARD_ID_NULL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.delazari.java_spring_api.entities.Card;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest
public class CardRepositoryTest {
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@BeforeEach
	public void init() {
		VALID_CARD_ID_NULL.setId(null);
	}
	
	//LIST OF CREATION TESTS.
	
	@Test
	public void create_WithValidData_ReturnCard() {
		Card card = cardRepository.save(VALID_CARD_ID_NULL);
		
		Card sut = testEntityManager.find(Card.class, card.getId());
		
		assertThat(sut).isNotNull();
		assertThat(sut.getName()).isEqualTo(card.getName());
		assertThat(sut.getDescription()).isEqualTo(card.getDescription());
	}
	
	@Test
	public void create_WithTheExistingName_ReturnDataIntegrityViolationException() {
		Card card = testEntityManager.persistAndFlush(VALID_CARD_ID_NULL);
		testEntityManager.detach(card);
		card.setId(null);
		
		assertThatThrownBy(() -> cardRepository.save(card)).isInstanceOf(DataIntegrityViolationException.class);
	}
	
	@Test
	public void create_WithTheNullObject_ReturnConstraintViolationException() {
		assertThatThrownBy(() -> cardRepository.save(INVALID_CARD_NULL)).isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	public void create_WithTheNameNull_ReturnConstraintViolationException() {
		assertThatThrownBy(() -> cardRepository.save(INVALID_CARD_EMPTY_NAME_FIELD)).isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	public void create_WithTheLongName_ReturnDataIntegrityViolationException() {
		assertThatThrownBy(() -> cardRepository.save(INVALID_CARD_LONG_NAME_FIELD)).isInstanceOf(DataIntegrityViolationException.class);
	}
	
	//LIST OF FIND TESTS
	
	@Test
	public void findById_WithValidID_ReturnCard() {
		Card card = testEntityManager.persistAndFlush(VALID_CARD_ID_NULL);
		
		Card sut = cardRepository.findById(card.getId()).get();
		
		assertThat(sut).isEqualTo(card);
	}
	
	@Test
	public void findById_WithIdNotFound_ReturnNoSuchElementException() {
		testEntityManager.persistAndFlush(VALID_CARD_ID_NULL);
		
		assertThatThrownBy(() -> cardRepository.findById(2L).get()).isInstanceOf(NoSuchElementException.class);
	}
	
	@Test
	public void findById_WithNullId_ReturnNoSuchElementException() {
		testEntityManager.persistAndFlush(VALID_CARD_ID_NULL);
		
		assertThatThrownBy(() -> cardRepository.findById(null).get()).isInstanceOf(InvalidDataAccessApiUsageException.class);
	}
	
	//LIST OF DELETE TESTS
	
	@Test
	public void deleteById_WithValidID_ReturnVoid() {
		testEntityManager.persistAndFlush(VALID_CARD_ID_NULL);
		
		assertThatCode(() -> cardRepository.deleteById(1L)).doesNotThrowAnyException();
	}
	
	@Test
	public void deleteById_WithNullId_ReturnInvalidDataAccessApiUsageException() {
		testEntityManager.persistAndFlush(VALID_CARD_ID_NULL);
		
		assertThatThrownBy(() -> cardRepository.deleteById(null)).isInstanceOf(InvalidDataAccessApiUsageException.class);
	}
}