package clueGame;

import java.util.ArrayList;

public abstract class Player {
	private String name;
	private String color;
	private int row, col;
	private boolean isHuman;
	private ArrayList<Card> cards;

	
	public void updateHand(Card card) {
		
	}
	
	public boolean getIsHuman() {
		return isHuman;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}

}
