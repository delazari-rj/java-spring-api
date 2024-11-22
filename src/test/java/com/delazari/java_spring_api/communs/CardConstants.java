package com.delazari.java_spring_api.communs;

import com.delazari.java_spring_api.entities.Card;

public class CardConstants {

	private static String longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse bibendum quis ante eu tempus. Morbi vitae porta sem, nec tristique elit. Maecenas semper aliquet risus, eget maximus libero vestibulum sed. Phasellus ut dignissim nisl, non consectetur tortor. Donec id tempor ligula. Suspendisse eleifend velit sit amet dignissim varius. Donec consequat neque eu velit blandit imperdiet. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Suspendisse varius neque quam. Nunc vestibulum diam vitae eros varius, non maximus massa tempus.\r\n"
			+ "\r\n"
			+ "Fusce ac rhoncus mi, eu auctor nunc. Aenean feugiat sit amet lectus at volutpat. Praesent rhoncus lorem sed urna tincidunt, quis dictum nulla suscipit. Donec eu velit molestie, porta nunc nec, dignissim mauris. Ut ac interdum tellus. Integer aliquam vitae risus eu malesuada. Mauris venenatis mi quis neque condimentum dignissim. In faucibus velit a quam malesuada, vel efficitur leo fermentum. Donec augue nunc, consequat ut est vitae, luctus tincidunt lectus. Nam vitae turpis in nibh sollicitudin molestie et in lacus. Morbi dignissim malesuada mattis. Cras placerat tempus leo, faucibus condimentum diam sollicitudin non. Pellentesque odio diam, ornare vel ex at, maximus cursus mauris. Cras arcu nisi, imperdiet vitae risus et, ultricies ullamcorper purus. Integer vel tortor sagittis, vestibulum lectus eget, placerat tortor. Aenean efficitur vitae ipsum ac aliquet.";
	
	private static String description = "Welcome to Magic: The Gathering! Magic™ is a trading card game where players summon incredible creatures and cast powerful spells to defeat their foes. The game has thousands of cards so you can always find a way to express yourself on the battlefield.";
	
	private static String name = "Birds of Paradise";
	
	public static final Card VALID_CARD_ID_NULL
			= new Card(null, name, description);
	
	public static final Card VALID_CARD_ID_1L
			= new Card(1L, name, description);
	
	public static final Card VALID_CARD_UPDATED 
			= new Card(1L, "Birds of Paradise UPDATED", "Welcome to Magic: The Gathering! UPDATED...");
	
	public static final Card INVALID_CARD_NULL 
			= new Card();
	
	public static final Card INVALID_CARD_EMPTY_NAME_FIELD 
			= new Card(null, "", description);
	
	public static final Card INVALID_CARD_LONG_NAME_FIELD 
			= new Card(null, longText, description);
 
}
