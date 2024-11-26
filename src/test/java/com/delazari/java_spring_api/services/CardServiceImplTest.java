package com.delazari.java_spring_api.services;

import static com.delazari.java_spring_api.communs.CardConstants.INVALID_CARD_NULL;
import static com.delazari.java_spring_api.communs.CardConstants.VALID_CARD_ID_1L;
import static com.delazari.java_spring_api.communs.CardConstants.VALID_CARD_ID_NULL;
import static com.delazari.java_spring_api.communs.CardDTOConstants.VALID_CARDDTO_ID_1L;
import static com.delazari.java_spring_api.communs.CardDTOConstants.VALID_CARDDTO_ID_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.delazari.java_spring_api.entities.dtos.CardDTO;
import com.delazari.java_spring_api.entities.services.impl.CardServiceImpl;
import com.delazari.java_spring_api.repositories.CardRepository;

@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {
	
	@InjectMocks
	private CardServiceImpl cardServiceImpl;
	
	@Mock
	private CardRepository cardRepository;
	
	@BeforeEach
	public void init() {
		VALID_CARD_ID_NULL.setId(null);
	}
	
	//*** CREATION UNIT TESTS. ***
	
	@Test
	public void create_WithValidData_ReturnCard() {
		when(cardRepository.save(VALID_CARD_ID_NULL)).thenReturn(VALID_CARD_ID_1L);
		CardDTO sut =  cardServiceImpl.create(VALID_CARDDTO_ID_NULL);
		assertThat(sut).isEqualTo(VALID_CARDDTO_ID_1L);
	}
	
	@Test
	public void create_WithInvalidNameValue_ReturnDataIntegrityViolationException() {
		//WithTheExistingName and WithTheLongName
		when(cardRepository.save(VALID_CARD_ID_NULL))
			.thenThrow(DataIntegrityViolationException.class);
		assertThatThrownBy(()-> cardServiceImpl.create(VALID_CARDDTO_ID_NULL))
			.isInstanceOf(DataIntegrityViolationException.class);
	}
	
	@Test
	public void create_WithTheNullObject_ReturnConstraintViolationException() {
		//WithTheNameNull and WithTheNullObject
		when(cardRepository.save(INVALID_CARD_NULL))
			.thenThrow(ConstraintViolationException.class);
		assertThatThrownBy(()-> cardServiceImpl.create(VALID_CARDDTO_ID_NULL))
			.isInstanceOf(IllegalArgumentException.class);
	}
	
	//*** FIND INTEGRATION TESTS ***
	
	@Test
	public void findById_WithValidID_ReturnCard() {
		when(cardRepository.findById(1L)).thenReturn(Optional.of(VALID_CARD_ID_1L));
		CardDTO sut =  cardServiceImpl.getById(1L);
		assertThat(sut).isEqualTo(VALID_CARDDTO_ID_1L);
	}
	
	@Test
	public void findById_WithIdNotFound_ReturnNoSuchElementException() {
		when(cardRepository.findById(2L))
			.thenThrow(NoSuchElementException.class);
		assertThatThrownBy(()-> cardServiceImpl.getById(2L))
			.isInstanceOf(NoSuchElementException.class);
	}
	
	@Test
	public void findById_WithNullId_ReturnDataAccessApiUsageException() {
		when(cardRepository.findById(null))
			.thenThrow(InvalidDataAccessApiUsageException.class);
		assertThatThrownBy(()-> cardServiceImpl.getById(null))
			.isInstanceOf(InvalidDataAccessApiUsageException.class);
	}
	
}