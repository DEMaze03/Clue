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
		// implement board accusation handling
		Board board = Board.getInstance();
		System.out.println(room + " " + person + " " + weapon);
		room = board.getDeck().get(accusation.getRoomSelection());
		person = board.getDeck().get(accusation.getPersonSelection());
		weapon = board.getDeck().get(accusation.getWeaponSelection());
		if (board.checkAccusation(room, person, weapon)) {
			JOptionPane.showMessageDialog(null, "You Win!", "A Message From Within...", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Oops! You Lose!", "A Message From Within...", JOptionPane.INFORMATION_MESSAGE);
		}
		dialog.dispose();
	}

}
