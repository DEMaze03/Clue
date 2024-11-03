/*
 * CardType - an enum for the different types of cards
 * 
 * Author: Elijas Sliva
 */

package clueGame;

public enum CardType {
	
	ROOM("ROOM"),
	PERSON("PERSON"),
	WEAPON("WEAPON");

	private String type;
	
	CardType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

}
