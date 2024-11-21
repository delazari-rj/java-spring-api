package com.delazari.java_spring_api.entities.dtos;

import com.delazari.java_spring_api.entities.Card;

public class CardDTO {
	private Long id;
	private String name;
	private String description;
	
	public CardDTO() {}
	
	public CardDTO(Card card) {
		this.id = card.getId();
		this.name = card.getName();
		this.description = card.getDescription();
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
