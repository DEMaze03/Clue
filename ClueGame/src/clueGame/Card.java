/*
 * Card - class for each card in the game. Has a name and a card type
 * 
 * Author: Elijas Sliva
 */

package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	private Player owner;
	
	public Card(String cardName, CardType cardType) {
		this.cardName = cardName;
		this.cardType = cardType;
	}
	
	//override for .equals() to accurately compare cards
	public boolean equals(Card target) {		
		if (!this.cardType.equals(target.getCardType())) {
			return false;
		}
		if (!this.cardName.equals(target.getCardName())) {
			return false;
		}
		
		return true;
	}
	
	//setters
	public void setOwner(Player p) {
		this.owner = p;
	}
	
	//getters
	public CardType getCardType() {
		return cardType;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public Player getOwner() {
		return owner;
	}

}
