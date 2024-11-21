package com.delazari.java_spring_api.communs;

import com.delazari.java_spring_api.entities.Card;

public class CardConstants {

	public static final Card VALID_CARD_ID_NULL
			= new Card(null, "Birds of Paradise", "Welcome to Magic: The Gathering! Magic™ is a trading card game where players summon incredible creatures and cast powerful spells to defeat their foes. The game has thousands of cards so you can always find a way to express yourself on the battlefield.");
	
	public static final Card VALID_CARD_ID_1L
			= new Card(1L, "Birds of Paradise", "Welcome to Magic: The Gathering! Magic™ is a trading card game where players summon incredible creatures and cast powerful spells to defeat their foes. The game has thousands of cards so you can always find a way to express yourself on the battlefield.");
	
	public static final Card VALID_CARD_UPDATED 
			= new Card(1L, "Birds of Paradise UPDATED", "Welcome to Magic: The Gathering! UPDATED...");
	
	public static final Card INVALID_CARD_NULL 
			= new Card();
	
	public static final Card INVALID_CARD_EMPTY_NAME_FIELD 
			= new Card(null, "", "Welcome to Magic: The Gathering! Magic™ is a trading card game where players summon incredible creatures and cast powerful spells to defeat their foes. The game has thousands of cards so you can always find a way to express yourself on the battlefield.");
}
