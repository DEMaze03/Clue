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
			
		}
//		JOptionPane.showMessageDialog(null, "you clicked the button! " + player.getName(), "NEXT BUTTON", JOptionPane.INFORMATION_MESSAGE);
		
		
		Board.currentPlayerIdx++;
		
		mainClass.setTurn(board.getCurrentPlayer(), board.roll());
		
    }

}
