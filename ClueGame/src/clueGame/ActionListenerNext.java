package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ActionListenerNext implements ActionListener {
	
    public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "you clicked the button!", "NEXT BUTTON", JOptionPane.INFORMATION_MESSAGE);
    }

}
