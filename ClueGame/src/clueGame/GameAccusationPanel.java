package clueGame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GameAccusationPanel extends JDialog {
	
	public GameAccusationPanel() {
		setTitle("Make an Accusation");
		setSize(350,150);
		setLayout(new GridLayout(4,2));
		
		JLabel roomLabel = new JLabel("Current Room:");
		JLabel personLabel = new JLabel("Person:");
		JLabel weaponLabel = new JLabel("Weapon:");
		
		String[] choices = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
		
		JComboBox<String> roomText = new JComboBox<String>(choices);
		JComboBox<String> personText = new JComboBox<String>(choices);
		JComboBox<String> weaponText = new JComboBox<String>(choices);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListenerAccusationSubmit());
		JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListenerCancel());

		add(roomLabel);
		add(roomText);
		add(personLabel);
		add(personText);
		add(weaponLabel);
		add(weaponText);
		add(submit);
		add(cancel);

	}
	
	public static void main(String[] args) {
		JDialog box = new GameAccusationPanel();
		box.setVisible(true);
	}

}
