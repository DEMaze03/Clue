package clueGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GameCardPanel extends JPanel {
	private Player player;
	private JPanel peoplePanel;
	private JPanel peopleHand;
	private JPanel peopleSeen;
	private JPanel roomPanel;
	private JPanel roomHand;
	private JPanel roomSeen;
	private JPanel weaponPanel;
	private JPanel weaponHand;
	private JPanel weaponSeen;
	private JPanel twinPane;
	
	
	// default constructor 
	public GameCardPanel(HumanPlayer player) {
		// create known card panel
		this.player = player;
		this.setLayout(new GridLayout(1,0));
		twinPane = new JPanel();
		twinPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Known Cards"));
		twinPane.setLayout(new GridLayout(3,0));
		
		
		// create known people category
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
		
		// create known room category
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
		
		// create known weapon category
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
		
		// for every card in the player hand, add it to the correct category.
		for(Card card : player.getCards()) {
				switch(card.getCardType()) {
				case CardType.ROOM:
					JTextField roomField = new JTextField(card.getCardName());
					roomField.setEditable(false);
					roomField.setBackground(card.getOwner().getColorObject());
					roomHand.add(roomField);
					
				break;
				case CardType.PERSON:
					JTextField personField = new JTextField(card.getCardName());
					personField.setEditable(false);
					personField.setBackground(card.getOwner().getColorObject());
					peopleHand.add(personField);
				break;
				case CardType.WEAPON:
					JTextField weaponField = new JTextField(card.getCardName());
					weaponField.setEditable(false);
					weaponField.setBackground(card.getOwner().getColorObject());
					weaponHand.add(weaponField);
				break;
				}
		}
	}
	
	// refresh panel function
	public void updatePanels() {
		// remove all cards and then add them back
		roomSeen.removeAll();
		roomHand.removeAll();
		peopleSeen.removeAll();
		peopleHand.removeAll();
		weaponSeen.removeAll();
		weaponHand.removeAll();
		
		for(Card card : player.getSeenCards()) {
			if((player.getCards().contains(card)) == false){
				switch(card.getCardType()) {
				case CardType.ROOM:
					JTextField roomField = new JTextField(card.getCardName());
					roomField.setEditable(false);
					roomField.setBackground(card.getOwner().getColorObject());
					roomSeen.add(roomField);
					
				break;
				case CardType.PERSON:
					JTextField personField = new JTextField(card.getCardName());
					personField.setEditable(false);
					personField.setBackground(card.getOwner().getColorObject());
					peopleSeen.add(personField);
				break;
				case CardType.WEAPON:
					JTextField weaponField = new JTextField(card.getCardName());
					weaponField.setEditable(false);
					weaponField.setBackground(card.getOwner().getColorObject());
					weaponSeen.add(weaponField);
				break;
				}
			}
		}
		for(Card card : player.getCards()) {
			switch(card.getCardType()) {
			case CardType.ROOM:
				JTextField roomField = new JTextField(card.getCardName());
				roomField.setEditable(false);
				roomField.setBackground(card.getOwner().getColorObject());
				roomHand.add(roomField);
				
			break;
			case CardType.PERSON:
				JTextField personField = new JTextField(card.getCardName());
				personField.setEditable(false);
				personField.setBackground(card.getOwner().getColorObject());
				peopleHand.add(personField);
			break;
			case CardType.WEAPON:
				JTextField weaponField = new JTextField(card.getCardName());
				weaponField.setEditable(false);
				weaponField.setBackground(card.getOwner().getColorObject());
				weaponHand.add(weaponField);
			break;
			}
	}
		twinPane.add(peoplePanel);
		twinPane.add(roomPanel);
		twinPane.add(weaponPanel);	
		this.add( twinPane ) ;   // causes swing to either add or read the entire panel and recalculate it
		
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		
		HumanPlayer player = new HumanPlayer("Waluigi", "Purple", 0, 0, true);
		Player player2 = new ComputerPlayer("Wario", "Yellow", 1,1,false);
		
		Player player3 = new ComputerPlayer("Craig","Red",2,2,false);
		Card seenCard2 = new Card("Wendys Chili", CardType.WEAPON);
		Card seenCard3 = new Card("Waluigi", CardType.PERSON);
		player3.updateHand(seenCard2);
		player3.updateHand(seenCard3);
		
		Card card = new Card("McDonalds Bathroom", CardType.ROOM);
		Card seenCard = new Card("Big Mac", CardType.WEAPON);
		GameCardPanel cardPanel = new GameCardPanel(player);
		player.updateHand(card);
		player2.updateHand(seenCard);
		player.updateSeenCard(seenCard);
		
		cardPanel.updatePanels();
		player.updateSeenCard(seenCard2);
		player.updateSeenCard(seenCard3);
		cardPanel.updatePanels();
		frame.setContentPane(cardPanel);
		frame.setSize(180, 800);  // size the frame
		frame.setVisible(true); // make it visible
		
		
		//cardPanel.updatePanels();
		
		
		
		
	}
}
