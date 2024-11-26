package clueGame;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GameSuggestionPanel extends JDialog {
	
	public GameSuggestionPanel(Board board) {
		setTitle("Make a Suggestion");
		setSize(350,150);
		setLayout(new GridLayout(4,2));
		
		JLabel roomLabel = new JLabel("Current Room:");
		JLabel personLabel = new JLabel("Person:");
		JLabel weaponLabel = new JLabel("Weapon:");
		
		ArrayList<String> peopleNames = board.getPlayerNames();
		ArrayList<String> weaponNames = board.getWeaponNames();
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
		String roomName = board.getRoom(board.getCell(board.getCurrentPlayer().getRow(), board.getCurrentPlayer().getCol())).getName();
		JTextField roomText = new JTextField(roomName);
		roomText.setEditable(false);
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
		//JDialog box = new GameSuggestionPanel();
		//box.setVisible(true);
	}
	
	

}
