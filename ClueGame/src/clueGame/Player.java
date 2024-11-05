package clueGame;

/*
 * Player - Parent class used for all players in the game
 * 
 * Authors: Daylon Maze & Elijas Sliva
 */

import java.util.ArrayList;

public abstract class Player {
	private String name;
	private String color;
	private int row, col;
	private boolean isHuman;
	private ArrayList<Card> cards;
	private ArrayList<Card> seenCards;

	
	public Player(String name, String color, int row, int col, boolean isHuman) {
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
		this.isHuman = isHuman;
		this.cards = new ArrayList<Card>();
	}
	
	//updateHand() - adds the provided card to the cards arrayList
	public void updateHand(Card card) {
		cards.add(card);
		updateSeenCard(card);
	}
	
	public void updateSeenCard(Card card) {
		seenCards.add(card);
	}
	
	public Card disproveSuggestion(Card card) {
		if (this.cards.contains(card)) {
			return card;
		} else {
			return null;
		}
	}
	
	public Solution createSuggestion() {
		Card room = new Card("FIX THIS", CardType.ROOM);
		Card person = new Card("FIX THIS", CardType.PERSON);
		Card weapon = new Card("FIX THIS", CardType.WEAPON);
		Solution solution = new Solution(room, person, weapon);
		return solution;
	}
	
	public BoardCell selectTarget(Board board, int roll) {
		return board.getCell(this.getRow(), this.getCol());
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
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public String getColor() {
		return color;
	}

}
