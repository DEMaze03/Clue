package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ActionListenerAccusation implements ActionListener {
	
	Board board;
	
    public void actionPerformed(ActionEvent e) {
//		JOptionPane.showMessageDialog(null, "you clicked the accusation button!", "MAKE ACCUSATION", JOptionPane.INFORMATION_MESSAGE);
		board = Board.getInstance();
    	JDialog window = new GameAccusationPanel(board);
		window.setVisible(true);
    }

}
