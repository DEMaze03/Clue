package clueGame;

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
				if (board.handleSuggestion(board.getHuman(),sug) == null) {
					JOptionPane.showMessageDialog(null, "You Win!", "A Message From Within...", JOptionPane.INFORMATION_MESSAGE);
				}else {
					System.out.println(board.handleSuggestion(board.getHuman(),sug).getCardName());
					board.getCurrentPlayer().updateSeenCard(board.handleSuggestion(board.getHuman(),sug));
					((GameCardPanel) ClueGame.cards).updatePanels();
					
				}
				dialog.dispose();
	}

}
