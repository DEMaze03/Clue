package clueGame;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Player - Parent class used for all players in the game
 * 
 * Authors: Daylon Maze & Elijas Sliva
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	private int row, col;
	private boolean isHuman;
	private ArrayList<Card> cards;
	private ArrayList<Card> seenCards;
	private boolean turnStatus = false;
	private int roomOffset = (int)(Math.random() * (20 + 20 + 1)) - 20;

	
	public Player(String name, String color, int row, int col, boolean isHuman) {
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
		this.isHuman = isHuman;
		this.cards = new ArrayList<Card>();
		this.seenCards = new ArrayList<Card>();
	}
	
	//updateHand() - adds the provided card to the cards arrayList
	public void updateHand(Card card) {
		cards.add(card);
		updateSeenCard(card);
		card.setOwner(this);
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
	
	public void draw(Graphics g, int xCoord, int yCoord, int width, int height) {		
		g.setColor(this.getColorObject());
		if(Board.getInstance().getCell(this.getRow(), this.getCol()).isRoomCenter()) {
			
			g.fillOval(xCoord+roomOffset, yCoord, width, height);
		}else {
			g.fillOval(xCoord, yCoord, width, height);
		}
		
	}
	
	public Solution createSuggestion(Board board) {
		Card room = new Card("FIX THIS", CardType.ROOM);
		Card person = new Card("FIX THIS", CardType.PERSON);
		Card weapon = new Card("FIX THIS", CardType.WEAPON);
		Solution solution = new Solution(room, person, weapon);
		return solution;
	}
	
	public BoardCell selectTarget(Board board, int roll) {
		return board.getCell(0, 0);
		}
		
	
	//SETTERS
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void setTurnStatus(boolean status) {
		this.turnStatus = status;
	}
		
	//GETTERS
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
	
	public Color getColorObject() {
		if(color.equals("Red")) {
			return Color.red;
		}else if(color.equals("Orange")) {
			return Color.orange;
		}else if(color.equals("Blue")) {
			return Color.blue;
		}else if(color.equals("White")) {
			return Color.white;
		}else if(color.equals("Green")) {
			return Color.green;
		}else if(color.equals("Purple")) {
			return new Color(175,0,255);
		}else {
			return Color.black;
		}
	}
	
	public ArrayList<Card> getSeenCards(){
		return seenCards;
	}

	public boolean getTurnStatus() {
		return this.turnStatus;
	}
	
}
