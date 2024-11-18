package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ActionListenerAccusation implements ActionListener {
	
    public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "you clicked the accusation button!", "MAKE ACCUSATION", JOptionPane.INFORMATION_MESSAGE);
    }

}
