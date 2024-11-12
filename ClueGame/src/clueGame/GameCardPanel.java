package clueGame;

<<<<<<< HEAD
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GameCardPanel extends JPanel {
	Player player;
	JPanel peoplePanel;
	JPanel peopleHand;
	JPanel peopleSeen;
	JPanel roomPanel;
	JPanel roomHand;
	JPanel roomSeen;
	JPanel weaponPanel;
	JPanel weaponHand;
	JPanel weaponSeen;
	JPanel twinPane;
	
	public GameCardPanel(Player player) {
		this.player = player;
		this.setLayout(new GridLayout(1,0));
		twinPane = new JPanel();
		twinPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Known Cards"));
		twinPane.setLayout(new GridLayout(3,0));
		
		
		peoplePanel = new JPanel();
		peoplePanel.setLayout(new GridLayout(4,0));
		peoplePanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Known People:"));
		JLabel pSeen = new JLabel("Seen:");
		JLabel pInHand = new JLabel("In Hand:");
		twinPane.add(peoplePanel);
		peoplePanel.add(pInHand);
		peopleHand = new JPanel();
		peoplePanel.add(peopleHand);
		peoplePanel.add(pSeen);
		peopleSeen = new JPanel();
		peoplePanel.add(peopleSeen);
		
		
		roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(4,0));
		roomPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Known Rooms:"));
		JLabel rSeen = new JLabel("Seen:");
		JLabel rInHand = new JLabel("In Hand:");
		twinPane.add(roomPanel);
		roomPanel.add(rInHand);
		roomHand = new JPanel();
		roomPanel.add(roomHand);
		roomPanel.add(rSeen);
		roomSeen = new JPanel();
		roomPanel.add(roomSeen);
		
		weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(4,0));
		weaponPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Known Weapons:"));
		JLabel wSeen = new JLabel("Seen:");
		JLabel wInHand = new JLabel("In Hand:");
		twinPane.add(weaponPanel);
		weaponPanel.add(wInHand);
		weaponHand = new JPanel();
		weaponPanel.add(weaponHand);
		weaponPanel.add(wSeen);
		weaponSeen = new JPanel();
		weaponPanel.add(weaponSeen);
		
		for(Card card : player.getCards()) {
				switch(card.getCardType()) {
				case CardType.ROOM:
					JTextField roomField = new JTextField(card.getCardName());
					roomField.setEditable(false);
					roomHand.add(roomField);
					
				break;
				case CardType.PERSON:
					JTextField personField = new JTextField(card.getCardName());
					personField.setEditable(false);
					peopleHand.add(personField);
				break;
				case CardType.WEAPON:
					JTextField weaponField = new JTextField(card.getCardName());
					weaponField.setEditable(false);
					weaponHand.add(weaponField);
				break;
				}
		}
	}
	
	public void updatePanels() {
		twinPane.removeAll();
		for(Card card : player.getSeenCards()) {
			if((player.getCards().contains(card)) == false){
				switch(card.getCardType()) {
				case CardType.ROOM:
					JTextField roomField = new JTextField(card.getCardName());
					roomField.setEditable(false);
					roomSeen.add(roomField);
					
				break;
				case CardType.PERSON:
					JTextField personField = new JTextField(card.getCardName());
					personField.setEditable(false);
					peopleSeen.add(personField);
				break;
				case CardType.WEAPON:
					JTextField weaponField = new JTextField(card.getCardName());
					weaponField.setEditable(false);
					weaponSeen.add(weaponField);
				break;
				}
			}
			twinPane.add(peoplePanel);
			twinPane.add(roomPanel);
			twinPane.add(weaponPanel);
			
			add( twinPane ) ;   // causes swing to either add or readd the entire panel and recalculate it
		}
	}
	
	public static void main(String args[]) {
		Player player = new HumanPlayer("Waluigi", "Purple", 0, 0, true);
		Card card = new Card("McDonalds Bathroom", CardType.ROOM);
		Card seenCard = new Card("Big Mac", CardType.WEAPON);
		player.updateHand(card);
		player.updateSeenCard(seenCard);
		GameCardPanel cardPanel = new GameCardPanel(player);
		cardPanel.updatePanels();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setContentPane(cardPanel);
		frame.setSize(180, 800);  // size the frame
		frame.setVisible(true); // make it visible
	}
=======
import java.awt.*;

import javax.swing.*;

public class GameCardPanel extends JPanel{
	private JPanel peoplePanel = new JPanel();
	private JPanel peopleInHand = new JPanel();
	private JTextField peopleSeen = new JTextField();
	private JPanel roomPanel = new JPanel();
	private JTextField roomInHand = new JTextField();
	private JTextField roomSeen = new JTextField();
	private JTextField weaponInHand = new JTextField();
	private JTextField weaponSeen = new JTextField();
	private JPanel weaponPanel = new JPanel();
	JLabel cardLabel = new JLabel();
	JPanel cardPanel = new JPanel();
	
	public GameCardPanel() {
		
		peoplePanel.setSize(180, 300);
		peopleInHand.setLayout(new GridLayout(1,1));
		peopleInHand.add(new JTextField());
		
		peoplePanel.setLayout(new GridLayout(2,1));
		peoplePanel.add(peopleInHand, BorderLayout.CENTER);
		peoplePanel.add(peopleSeen, BorderLayout.CENTER);
		peoplePanel.setBorder(BorderFactory.createTitledBorder("Known People"));
		
		
		roomPanel.setSize(180, 300);
		roomInHand.setEditable(false);
		roomPanel.setLayout(new GridLayout(2,1));
		roomPanel.add(roomInHand, BorderLayout.CENTER);
		roomPanel.add(roomSeen, BorderLayout.CENTER);
		roomPanel.setBorder(BorderFactory.createTitledBorder("Known Rooms"));
		
		weaponPanel.setSize(180, 300);
		weaponPanel.setLayout(new GridLayout(2,1));
		weaponPanel.add(weaponInHand, BorderLayout.CENTER);
		weaponPanel.add(weaponSeen, BorderLayout.CENTER);
		weaponPanel.setBorder(BorderFactory.createTitledBorder("Known Weapons"));

		
		cardLabel.setText("Known Cards");		
		cardPanel.setLayout(new GridLayout(3, 1));
		cardPanel.setSize(180, 700);
		cardPanel.add(peoplePanel);
		cardPanel.add(roomPanel);
		cardPanel.add(weaponPanel);
		cardPanel.setBorder(BorderFactory.createTitledBorder("Known Cards"));
		cardPanel.setVisible(true);
		
		this.setLayout(new BorderLayout());
		this.add(cardPanel);
		
		
	}
	

	public static void main(String[] args) {
		

		GameCardPanel gamePanel = new GameCardPanel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setContentPane(gamePanel);
		frame.setSize(180, 750);  // size the frame
		frame.setVisible(true); // make it visible
		

		
	}
	

>>>>>>> 8f86538647c919c311bcd70b97d247d92f1528d8
}
