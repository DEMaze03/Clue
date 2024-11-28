/*
 * ActionListenerSuggestionSubmit - action listener for the Suggestion subit button. Handles Player accusations
 * Authors: Daylon Maze & Elijas Sliva
 */

package clueGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ActionListenerSuggestionSubmit implements ActionListener {
	private GameSuggestionPanel suggestion;
	private JDialog dialog;
	Card room;
	Card person;
	Card weapon;
	
	public ActionListenerSuggestionSubmit(GameSuggestionPanel dialog) {
		this.dialog = dialog;
		this.suggestion = dialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//get the data from the drop down menu from the Suggestion menu and make a new suggestion from those
				Board board = Board.getInstance();
				room = board.getDeck().get(suggestion.getRoomSelection());
				person = board.getDeck().get(suggestion.getPersonSelection());
				weapon = board.getDeck().get(suggestion.getWeaponSelection());
				Solution sug = new Solution(room,person,weapon);
				//move the suggested player to the room that's being suggested and repaint
				board.getPlayers().get(sug.getPerson().getCardName()).setRow(board.getHuman().getRow());
				board.getPlayers().get(sug.getPerson().getCardName()).setCol(board.getHuman().getCol());
				Board.getInstance().repaint();
				((GameControlPanel) ClueGame.control).setGuess(person.getCardName(), room.getCardName(), weapon.getCardName(), board.getHuman().getColorObject());
				//if the suggestion can't be proven, inform the player, otherwise, inform the player who disproved it and update the player's seen cards
				if (board.handleSuggestion(board.getHuman(),sug) == null) {
					((GameControlPanel) ClueGame.control).setGuessResult("Unable to be Disproven!!!", Color.WHITE);
					((GameCardPanel) ClueGame.cards).updatePanels();
				}else {
					//if the current player's seen card list doesn't contain the disproven card, add it to their seen list
					if(board.getCurrentPlayer().getSeenCards().contains(board.handleSuggestion(board.getHuman(),sug)) == false) {
						board.getCurrentPlayer().updateSeenCard(board.handleSuggestion(board.getHuman(),sug));
					}
					((GameControlPanel) ClueGame.control).setGuessResult("Suggestion Disproven!", board.handleSuggestion(board.getHuman(),sug).getOwner().getColorObject());
					((GameCardPanel) ClueGame.cards).updatePanels();
					
				}
				dialog.dispose();
	}

}
