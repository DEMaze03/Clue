package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class ActionListenerCancel implements ActionListener {
	
	JDialog cancel = new JDialog();
	
	public ActionListenerCancel(JDialog dialog) {
		cancel = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		cancel.dispose();
	}

}
