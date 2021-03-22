package cardGame;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow2 {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow2 window = new MainWindow2();
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
	public MainWindow2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Color backgroundColor = new  Color(0x65a167);
		frame = new JFrame();
		frame.getContentPane().setBackground(backgroundColor);//set window background color
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(new Color(0x606060));
		separator.setBounds(10, 250, 615, 2);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel = new JLabel("Dealer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(257, 10, 95, 33);
		frame.getContentPane().add(lblNewLabel);

		JTextPane paneStatus = new JTextPane();
		paneStatus.setBackground(backgroundColor);
		paneStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paneStatus.setBounds(31, 83, 141, 142);
		frame.getContentPane().add(paneStatus);
		paneStatus.setEditable(false);
		
		JLabel lblNewLabel2 = new JLabel("Player");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel2.setBounds(257, 279, 95, 33);
		frame.getContentPane().add(lblNewLabel2);

		JButton btnHitPlayer = new JButton("Hit");
		btnHitPlayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHitPlayer.setBounds(67, 334, 105, 37);
		frame.getContentPane().add(btnHitPlayer);
		btnHitPlayer.setEnabled(false);

		JButton btnStandPlayer = new JButton("Stand");
		btnStandPlayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStandPlayer.setBounds(67, 412, 105, 37);
		frame.getContentPane().add(btnStandPlayer);
		btnStandPlayer.setEnabled(false);

		JLabel lblScoreDealer = new JLabel("Score");
		lblScoreDealer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblScoreDealer.setBounds(48, 29, 132, 33);
		frame.getContentPane().add(lblScoreDealer);

		JLabel lblScorePlayer = new JLabel("Score");
		lblScorePlayer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblScorePlayer.setBounds(48, 279, 132, 33);
		frame.getContentPane().add(lblScorePlayer);
		
		JLabel lblBet = new JLabel("Bet");
		lblBet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBet.setBounds(460, 279, 50, 35);
		frame.getContentPane().add(lblBet);
		
		JSpinner betSpinner = new JSpinner();
		betSpinner.setBounds(500, 279, 70, 35);
		betSpinner.setValue(200);
		frame.getContentPane().add(betSpinner);
		
		JButton btnDeal = new JButton("Deal");
		btnDeal.setBounds(194, 516, 210, 37);
		frame.getContentPane().add(btnDeal);
		
		JLabel lblBank = new JLabel("");
		lblBank.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBank.setHorizontalAlignment(SwingConstants.CENTER);
		lblBank.setBounds(20, 516, 152, 37);
		frame.getContentPane().add(lblBank);
		
		String minBetStr = "Minimum Bet: " + Player.minBet;
		JLabel lblMinBet = new JLabel(minBetStr);
		lblMinBet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMinBet.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinBet.setBounds(429, 516, 173, 37);
		frame.getContentPane().add(lblMinBet);
		
		JLabel lblMoney = new JLabel("");
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoney.setBounds(102, 502, 70, 24);
		frame.getContentPane().add(lblMoney);
		
		Game game = new Game(frame, btnDeal, paneStatus,lblScoreDealer, btnHitPlayer, btnStandPlayer, lblScorePlayer, lblBank, lblMoney, betSpinner);
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.deal();
			}
		});
		btnHitPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.hit();
			}
		});
		btnStandPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.stand();
			}
		});
		
	}
}
