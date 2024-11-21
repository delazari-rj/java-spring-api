package com.delazari.java_spring_api.mappers;

import com.delazari.java_spring_api.entities.Card;
import com.delazari.java_spring_api.entities.dtos.CardDTO;

public class CardMapper {
	
	public static CardDTO mapToCardDTO(Card card) {
		return new CardDTO(card);
	}
	
	public static Card mapToCard(CardDTO cardDto) {
		return new Card(cardDto.getId(), cardDto.getName(), cardDto.getDescription());
	}
}
