package com.delazari.java_spring_api.services;

import static com.delazari.java_spring_api.communs.CardConstants.INVALID_CARD_EMPTY_NAME_FIELD;
import static com.delazari.java_spring_api.communs.CardConstants.VALID_CARD_ID_1L;
import static com.delazari.java_spring_api.communs.CardConstants.VALID_CARD_ID_NULL;
import static com.delazari.java_spring_api.communs.CardDTOConstants.INVALID_CARDDTO_EMPTY_NAME_FIELD;
import static com.delazari.java_spring_api.communs.CardDTOConstants.VALID_CARDDTO_ID_1L;
import static com.delazari.java_spring_api.communs.CardDTOConstants.VALID_CARDDTO_ID_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.delazari.java_spring_api.entities.dtos.CardDTO;
import com.delazari.java_spring_api.entities.services.impl.CardServiceImpl;
import com.delazari.java_spring_api.repositories.CardRepository;

import jakarta.validation.ConstraintViolationException;

@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {
	
	@InjectMocks
	private CardServiceImpl cardServiceImpl;
	
	@Mock
	private CardRepository cardRepository;
	
	@Test
	public void createCard_WithValidData_ReturnCard() {
		when(cardRepository.save(VALID_CARD_ID_NULL)).thenReturn(VALID_CARD_ID_1L);
		CardDTO sut =  cardServiceImpl.create(VALID_CARDDTO_ID_NULL);
		assertThat(sut).isEqualTo(VALID_CARDDTO_ID_1L);
	}
	
	@Test
	public void createCard_WithEmptyName_ThrowsConstraintViolationException() {
		when(cardRepository.save(INVALID_CARD_EMPTY_NAME_FIELD)).thenThrow(ConstraintViolationException.class);
		assertThatThrownBy(()-> cardServiceImpl.create(INVALID_CARDDTO_EMPTY_NAME_FIELD)).isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void createCard_WithSameName_ThrowConstraintViolationException() {
		when(cardRepository.save(VALID_CARD_ID_NULL)).thenThrow(DataIntegrityViolationException.class);
		assertThatThrownBy(()-> cardServiceImpl.create(VALID_CARDDTO_ID_NULL)).isInstanceOf(IllegalArgumentException.class);
	}

}
