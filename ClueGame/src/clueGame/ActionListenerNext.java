/*
 * ActionListenerNext - action listener for the Next button on the control panel
 * Authors: Daylon Maze & Elijas Sliva
 */

package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ActionListenerNext implements ActionListener {
	Board board = Board.getInstance();
	private GameControlPanel mainClass;
	
	public ActionListenerNext(GameControlPanel mainClass) {
		this.mainClass = mainClass;
	}
	
    public void actionPerformed(ActionEvent e) {
		Player player = board.getCurrentPlayer();
		if (player.getIsHuman()) {
			//if player turn isn't done, show error, otherwise have player move to that cell
			if(player.getTurnStatus()) {
				Board.currentPlayerIdx++;
				board.roll();
				mainClass.setTurn(board.getCurrentPlayer(), board.getRoll());
				board.repaint();
			}else {
				JOptionPane.showMessageDialog(null, "Please finish your turn!", "A Message From Within...", JOptionPane.INFORMATION_MESSAGE);
			}
		}else {
			
			//increase the board's player index, reroll the dice, set the new turn for the control panel, and repaint
			Board.currentPlayerIdx++;
			board.roll();
			mainClass.setTurn(board.getCurrentPlayer(), board.getRoll());
			board.repaint();
		}
		
    }

}
