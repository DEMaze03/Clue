/*
 * Solution - class used to store the answer to the mystery. Stores 3 cards, one room, one person, and one weapon
 * 
 * Authors: Daylon Maze & Elijas Sliva
 */

package clueGame;

public class Solution {
	private Card room;
	private Card person;
	private Card weapon;
	
	public Solution(Card room, Card person, Card weapon) {
		super();
		this.room = room;
		this.person = person;
		this.weapon = weapon;
	}

	public Card getRoom() {
		return room;
	}

	public Card getPerson() {
		return person;
	}

	public Card getWeapon() {
		return weapon;
	}

	
	

}
