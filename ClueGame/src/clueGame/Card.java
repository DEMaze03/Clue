package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public Card(String cardName, CardType cardType) {
		this.cardName = cardName;
		this.cardType = cardType;
	}
	
	public boolean equals(Card target) {
		if (target.cardName.equals(this.cardName)) {
			return true;
		} else {
			return false;
		}
	}
	
	public CardType getCardType() {
		return cardType;
	}

}
