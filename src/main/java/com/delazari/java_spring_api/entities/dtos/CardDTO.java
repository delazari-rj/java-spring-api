package com.delazari.java_spring_api.entities.dtos;

import java.util.Objects;

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

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardDTO other = (CardDTO) obj;
		return Objects.equals(id, other.id);
	}
}
