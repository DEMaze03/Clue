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
		// implement board accusation handling
				Board board = Board.getInstance();
				room = board.getDeck().get(suggestion.getRoomSelection());
				person = board.getDeck().get(suggestion.getPersonSelection());
				weapon = board.getDeck().get(suggestion.getWeaponSelection());
				Solution sug = new Solution(room,person,weapon);
				board.getPlayers().get(sug.getPerson().getCardName()).setRow(board.getHuman().getRow());
				board.getPlayers().get(sug.getPerson().getCardName()).setCol(board.getHuman().getCol());
				Board.getInstance().repaint();
				((GameControlPanel) ClueGame.control).setGuess(person.getCardName(), room.getCardName(), weapon.getCardName(), board.getHuman().getColorObject());
				if (board.handleSuggestion(board.getHuman(),sug) == null) {
					((GameControlPanel) ClueGame.control).setGuessResult("Unable to be Disproven!!!", Color.WHITE);
					((GameCardPanel) ClueGame.cards).updatePanels();
				}else {
					System.out.println(board.handleSuggestion(board.getHuman(),sug).getCardName());
					if(board.getCurrentPlayer().getSeenCards().contains(board.handleSuggestion(board.getHuman(),sug))) {
						//do nothing
					}else {
						board.getCurrentPlayer().updateSeenCard(board.handleSuggestion(board.getHuman(),sug));
					}
					((GameControlPanel) ClueGame.control).setGuessResult("Suggestion Disproven!", board.handleSuggestion(board.getHuman(),sug).getOwner().getColorObject());
					((GameCardPanel) ClueGame.cards).updatePanels();
					
				}
				dialog.dispose();
	}

}
