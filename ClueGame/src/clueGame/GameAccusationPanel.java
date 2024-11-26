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
	private JComboBox<String> roomText;
	private JComboBox<String> personText;
	private JComboBox<String> weaponText;
	
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
		
		roomText = new JComboBox<String>(rooms);
		personText = new JComboBox<String>(people);
		weaponText = new JComboBox<String>(weapons);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListenerAccusationSubmit(this));
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
	
	public String getRoomSelection() {
		return (String) roomText.getSelectedItem();
	}
	
	public String getPersonSelection() {
		return (String) personText.getSelectedItem();
	}
	
	public String getWeaponSelection() {
		return (String) weaponText.getSelectedItem();
	}
	
	public static void main(String[] args) {
		//JDialog box = new GameAccusationPanel();
		//box.setVisible(true);
	}

}
