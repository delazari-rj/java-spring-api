package com.delazari.java_spring_api.communs;

import com.delazari.java_spring_api.entities.Card;
import com.delazari.java_spring_api.entities.dtos.CardDTO;
import static com.delazari.java_spring_api.mappers.CardMapper.mapToCardDTO;;

public class CardDTOConstants {
	
	public static final CardDTO VALID_CARDDTO_ID_NULL
			= mapToCardDTO(new Card(null, "Birds of Paradise", "Welcome to Magic: The Gathering! Magic™ is a trading card game where players summon incredible creatures and cast powerful spells to defeat their foes. The game has thousands of cards so you can always find a way to express yourself on the battlefield."));
	
	public static final CardDTO VALID_CARDDTO_ID_1L 
			= mapToCardDTO(new Card(1L, "Birds of Paradise", "Welcome to Magic: The Gathering! Magic™ is a trading card game where players summon incredible creatures and cast powerful spells to defeat their foes. The game has thousands of cards so you can always find a way to express yourself on the battlefield."));
	
	public static final CardDTO VALID_CARDDTO_UPDATED 
			= mapToCardDTO(new Card(1L, "Birds of Paradise UPDATED", "Welcome to Magic: The Gathering! UPDATED..."));
	
	public static final CardDTO INVALID_CARDDTO_NULL 
			= mapToCardDTO(new Card());
	
	public static final CardDTO INVALID_CARDDTO_EMPTY_NAME_FIELD 
			= mapToCardDTO(new Card(null, "", "Welcome to Magic: The Gathering! Magic™ is a trading card game where players summon incredible creatures and cast powerful spells to defeat their foes. The game has thousands of cards so you can always find a way to express yourself on the battlefield."));
}
