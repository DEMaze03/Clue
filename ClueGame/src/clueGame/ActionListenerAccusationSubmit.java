/*
 * ActionListenerAccusationSubmit - action listener for the accusation submit button. Handles Player Accusations
 * Authors: Daylon Maze & Elijas Sliva
 */

package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ActionListenerAccusationSubmit implements ActionListener {
	private GameAccusationPanel accusation;
	private JDialog dialog;
	Card room;
	Card person;
	Card weapon;
	
	public ActionListenerAccusationSubmit(GameAccusationPanel dialog) {
		this.dialog = dialog;
		this.accusation = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		//get the data from the drop down menu from the Accusation menu
		Board board = Board.getInstance();
		room = board.getDeck().get(accusation.getRoomSelection());
		person = board.getDeck().get(accusation.getPersonSelection());
		weapon = board.getDeck().get(accusation.getWeaponSelection());
		//check if accusation is correct. If it is, display a win message, if not, display a lose message and close the game
		if (board.checkAccusation(room, person, weapon)) {
			JOptionPane.showMessageDialog(null, "You Win!", "A Message From Within...", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}else {
			JOptionPane.showMessageDialog(null, "Oops! You Lose!", "A Message From Within...", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		dialog.dispose();
	}

}
