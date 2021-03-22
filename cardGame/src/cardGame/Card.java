package cardGame;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card {
	static Random random = new Random();
	static DeckImage deckImage = new DeckImage();
	static BufferedImage[][] images = initializeImages();
	private static BufferedImage flippedCard = deckImage.getImage(0, 13);
	
	private JLabel label;
	private BufferedImage image;
	private int suit;
	private int value;
	
	Card() {
		this.suit = random.nextInt(4);
		this.value = random.nextInt(13) + 1;
		this.image = images[suit][value-1];
		this.label = makeCardLabel(image);
	}
	
	public BufferedImage getFlipped() {
		return flippedCard;
	}
	
	public int getValue() {
		return value;
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public void removeFromWindow() {
		Container parent = label.getParent();
		parent.remove(label);
		parent.validate();
		parent.repaint();
	}
	
	public void resetImage() {
		label.setIcon(new ImageIcon(image));
	}
	
	private JLabel makeCardLabel(BufferedImage image) {
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(image));
		return label;
	}
	
	private static BufferedImage[][] initializeImages() {
		BufferedImage[][] images = new BufferedImage[4][13];
		for(int suit = 0; suit < 4; suit++) {
			for(int value = 0; value < 13; value++) {
				images[suit][value] = deckImage.getImage(suit,value);
			}
		}
		return images;
	}
}
