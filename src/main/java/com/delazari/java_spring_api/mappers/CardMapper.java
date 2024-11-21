package com.delazari.java_spring_api.mappers;

import com.delazari.java_spring_api.entities.Card;
import com.delazari.java_spring_api.entities.dtos.CardDTO;

public class CardMapper {
	
	public CardDTO mapToCardDTO(Card card) {
		return new CardDTO();
	}
	
	public Card mapToCard(CardDTO cardDto) {
		return new Card(cardDto.getId(), cardDto.getName(), cardDto.getDescription());
	}
}
