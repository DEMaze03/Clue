package clueGame;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GameAccusationPanel extends JDialog {
	
	public GameAccusationPanel(Board board) {
		setTitle("Make an Accusation");
		setSize(350,150);
		setLayout(new GridLayout(4,2));
		
		JLabel roomLabel = new JLabel("Current Room:");
		JLabel personLabel = new JLabel("Person:");
		JLabel weaponLabel = new JLabel("Weapon:");
		
		ArrayList<String> peopleNames = board.getPlayerNames();
		ArrayList<String> weaponNames = board.getWeaponNames();
		ArrayList<String> roomNames = board.getRoomNames();
		String[] people = new String[peopleNames.size()];
		int i = 0;
		for(String person : peopleNames) {
			people[i] = person;
			i++;
		}
		String[] weapons = new String[weaponNames.size()];
		i = 0;
		for(String weapon : weaponNames) {
			weapons[i] = weapon;
			i++;
		}
		String[] rooms = new String[roomNames.size()];
		i = 0;
		for(String room : roomNames) {
			rooms[i] = room;
			i++;
		}
		
		JComboBox<String> roomText = new JComboBox<String>(rooms);
		JComboBox<String> personText = new JComboBox<String>(people);
		JComboBox<String> weaponText = new JComboBox<String>(weapons);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListenerAccusationSubmit());
		JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListenerCancel(this));

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
		//JDialog box = new GameAccusationPanel();
		//box.setVisible(true);
	}

}
