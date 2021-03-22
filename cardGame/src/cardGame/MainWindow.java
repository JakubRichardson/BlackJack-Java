package cardGame;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainWindow {
	static Random random = new Random();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0x598d5a));
		frame.setBounds(100, 100, 240, 248);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel cardLabel1 = new JLabel("Image Goes Here");
		cardLabel1.setBounds(10, 10, 100, 147);
		frame.getContentPane().add(cardLabel1);
		
		JLabel cardLabel2 = new JLabel("Image Goes Here");
		cardLabel2.setBounds(120, 10, 100, 147);
		frame.getContentPane().add(cardLabel2);
		
		JButton btnNewButton = new JButton("Deal");
		btnNewButton.setBounds(10, 167, 210, 37);
		frame.getContentPane().add(btnNewButton);
		
		DeckImage deckImage = new DeckImage();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int suit1 = random.nextInt(4);
				int ordinal1 = random.nextInt(13);
				
				BufferedImage cardImage = deckImage.getImage(suit1, ordinal1);
				cardLabel1.setIcon(new ImageIcon(cardImage));
				
				int suit2 = random.nextInt(4);
				int ordinal2 = random.nextInt(13);
				
				BufferedImage cardImage2 = deckImage.getImage(suit2, ordinal2);
				cardLabel2.setIcon(new ImageIcon(cardImage2));
			}
		});
	}
}
