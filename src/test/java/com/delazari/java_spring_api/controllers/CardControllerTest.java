package com.delazari.java_spring_api.controllers;

import static com.delazari.java_spring_api.communs.CardDTOConstants.INVALID_CARDDTO_EMPTY_NAME_FIELD;
import static com.delazari.java_spring_api.communs.CardDTOConstants.VALID_CARDDTO_ID_1L;
import static com.delazari.java_spring_api.communs.CardDTOConstants.VALID_CARDDTO_ID_NULL;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.delazari.java_spring_api.entities.services.CardServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CardController.class)
class CardControllerTest {

	@Autowired
	private MockMvc mocMvc;
	
	@Autowired
	private ObjectMapper objMapper;
	
	@MockBean
	private CardServices cardService;
	
	@Test
	public void createCard_ValidData_ReturnCreate() throws Exception {
		when(cardService.create(VALID_CARDDTO_ID_NULL))
			.thenReturn(VALID_CARDDTO_ID_1L);
		mocMvc.perform(
				post("/cards")
					.content(objMapper.writeValueAsString(VALID_CARDDTO_ID_NULL))
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$").value(VALID_CARDDTO_ID_1L));
	}
	
	@Test
	public void createCard_WithEmptyName_ReturnIllegalArgumentException() throws Exception {
		when(cardService.create(INVALID_CARDDTO_EMPTY_NAME_FIELD))
			.thenThrow(new IllegalArgumentException("The value of the Name field is invalid."));
		mocMvc.perform(
				post("/cards")
					.content(objMapper.writeValueAsString(INVALID_CARDDTO_EMPTY_NAME_FIELD))
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void createCard_WithSameName_ReturnIllegalArgumentException() throws Exception {
		when(cardService.create(VALID_CARDDTO_ID_NULL))
			.thenThrow(new IllegalArgumentException("There is a card with the same name already registered."));
		mocMvc.perform(
				post("/cards")
					.content(objMapper.writeValueAsString(VALID_CARDDTO_ID_NULL))
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isConflict());
	}
	
	@Test
	public void getCardById_ValidId_ReturnOK() throws Exception{
		when(cardService.getById(anyLong()))
			.thenReturn(VALID_CARDDTO_ID_1L);
		mocMvc.perform(
				get("/cards/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(VALID_CARDDTO_ID_1L));
	}
}