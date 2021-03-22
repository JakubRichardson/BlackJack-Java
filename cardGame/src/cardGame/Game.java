package cardGame;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.Timer;

public class Game {
	Player player = new Player();
	Dealer dealer = new Dealer();
	final static int cardsX = 255;
	final static int cardSeperator = 20;

	JTextPane paneStatus;
	JLabel lblBank;
	JLabel lblMoney;
	JSpinner betSpinner;
	JButton btnHitPlayer;
	JButton btnStandPlayer;
	JLabel scoreDealer;
	JLabel scorePlayer;
	JFrame frame;
	JButton dealBtn;

	Game(JFrame frame, JButton dealBtn, JTextPane paneStatus,JLabel scoreDealer, JButton btnHitPlayer, JButton btnStandPlayer, JLabel scorePlayer, JLabel lblBank, JLabel lblMoney, JSpinner betSpinner) {
		this.frame = frame;
		this.dealBtn = dealBtn;
		this.paneStatus = paneStatus;
		this.btnHitPlayer = btnHitPlayer;
		this.btnStandPlayer = btnStandPlayer;
		this.scorePlayer = scorePlayer;
		this.scoreDealer = scoreDealer;
		this.lblBank = lblBank;
		this.lblMoney = lblMoney;
		this.betSpinner = betSpinner;
		setBank(player.getBankString());
	}

	public void deal(){
		if(tryBet() == true) {
			setStatus("");
			setBank(player.getBankString());
			addCard(player, false);
			addCard(player, false);
			addCard(dealer, false);
			addCard(dealer, true);
			setScore('P', player.getTotalString());
			setScore('D', dealer.getDealScore());
			dealBtn.setEnabled(false);
			if(checkBlackjack() == false) {
				setPlayerTurn(true);
			} else {
				Timer timer = new Timer(2500, event -> {
					setMoney("");
					nextTurn();
				});
				timer.setRepeats(false);
				timer.start();
			}
		} else {
			setStatus("Set a valid bet");
		}
	}

	public void hit() {
		addCard(player,false);
		setScore('P', player.getTotalString());
		if(player.getTotal() > 21) {
			setStatus("Bust, Dealer Wins");
			setPlayerTurn(false);
			lose();
			flipHiddenCard();
			Timer timer = new Timer(1500, end -> {
				nextTurn();
			});
			timer.setRepeats(false);
			timer.start();
		}
	}

	public void stand() {
		setStatus("Stand");
		setScore('P', Integer.toString(player.getTotal()));
		setPlayerTurn(false);
		Timer timer = new Timer(500, event -> {
			dealerTurn();
		});
		timer.setRepeats(false);
		timer.start();		
	}

	public boolean checkBlackjack() {
		if(player.getTotal() == 21) {
			blackjack();
			setStatus("You have BlackJack");
			setScore('P', player.getTotalString());
			setBank(player.getBankString());
			return true;
		} else if (dealer.getTotal() == 21) {
			flipHiddenCard();
			setStatus("Dealer has Blackjack");
			setScore('D', dealer.getTotalString());
			lose();
			return true;
		}
		return false;
	}

	private void addCard(Person person, boolean flipped) {
		Card card = new Card();
		if(flipped == true) {
			card.getLabel().setIcon(new ImageIcon(card.getFlipped()));
		}
		addCardLabelToFrame(card.getLabel(), person.y, person.hand.length);
		person.hand.append(card);
	}

	private void flipHiddenCard() {
		try {
			dealer.hand.head.next.data.resetImage();
			frame.revalidate();
			frame.repaint();
			setScore('D', dealer.getTotalString());
		}
		catch(Exception e) {}
	}

	private void clearCards() {
		Node<Card> node = player.hand.head;
		while(node != null) {
			Card card = node.data;
			card.removeFromWindow();
			node = node.next;
		}
		player.hand.clear();
		node = dealer.hand.head;
		while(node != null) {
			node.data.removeFromWindow();
			node = node.next;
		}
		dealer.hand.clear();
	}

	private void addCardLabelToFrame(JLabel label, int y, int xNum) {
		int x = cardsX + (cardSeperator * xNum);
		label.setBounds(x, y, 100, 147);
		frame.add(label);
		frame.revalidate();
		frame.repaint();
	}

	private void setScore(char person, String score) {
		if(person == 'D') {
			scoreDealer.setText(score);
		} else if(person == 'P') {
			scorePlayer.setText(score);
		}
	}

	private void setBank(String bank) {
		lblBank.setText("Bank: " + bank);
	}

	private void setPlayerTurn(boolean turn) {
		btnHitPlayer.setEnabled(turn);
		btnStandPlayer.setEnabled(turn);
	}

	private void setStatus(String str) {
		paneStatus.setText(str);
	}
	
	private String getStatus() {
		return paneStatus.getText();
	}
	
	private void setMoney(String str) {
		lblMoney.setText(str);
	}
	
	private void setMoneyColor(int color) {
		lblMoney.setForeground(new Color(color));
	}

	private void dealerTurn() {
		flipHiddenCard();
		Timer timer = new Timer(500, event -> {
			deal2();
		});
		timer.setRepeats(false);
		timer.start();
	}

	private int getBet() {
		return (int)betSpinner.getValue();
	}

	private boolean tryBet() {
		return player.setBet(getBet());
	}

	private void settleTurn() {
		int playerScore = player.getTotal();
		int dealerScore = dealer.getTotal();
		
		if(dealerScore > 21) {
			setStatus("You Win, Dealer Busted");
			win();
		}else if (dealerScore > playerScore) {
			setStatus("Dealer Wins");
			lose();
		} else if(dealer.getTotal() == player.getTotal()) {
			setStatus("Push");
			push();
		} else {
			setStatus("You Win");
			win();
		}
		Timer timer = new Timer(1500, event -> {
			setBank(player.getBankString());
			nextTurn();
		});
		timer.setRepeats(false);
		timer.start();
	}

	private void reset() {
		setPlayerTurn(false);
		setStatus("");
		setMoney("");
		setScore('P', "Score");
		setScore('D', "Score");
		clearCards();
		dealBtn.setEnabled(true);
	}
	
	private void lose() {
		setMoneyColor(0xFF0000);
		setMoney("+0");
		player.lose();
	}
	
	private void win() {
		setMoneyColor(0x00fff0);
		setMoney("+" + player.getWinBetString());
		player.win();
	}

	private void push() {
		setMoneyColor(0x0000ff);
		setMoney("+" + player.getBetString());
		player.push();
	}
	
	private void blackjack() {
		setMoneyColor(0x00ffff);
		setMoney("+" + player.getBlackjackString());
		player.blackJack();
	}
	
	private void deal2() {
		boolean completed = false;
		if(dealer.getTotal() < 17) {
			boolean added = false;
			while(dealer.getTotal() < 17) {
				if(added == false) {
					setStatus("Dealer has: " + dealer.getTotal() + " and Hits.");
					added = true;
				} else {
					setStatus(getStatus() + " Dealer has: " + dealer.getTotal() + " and Hits.");
				}
				addCard(dealer, false);
				setScore('D', dealer.getTotalString());
			}
			int score = dealer.getTotal();
			if(score > 21) {
				setStatus(getStatus() + " Dealer has " + score + " and Busts");
			} else {
				setStatus(getStatus() + " Dealer has " + dealer.getTotal() + " and Stands");
			}
			completed = true;
		} else {
			setStatus("Dealer has " + dealer.getTotal() + " and Stands");
			completed = true;
		}
		Timer timer = new Timer(2500, event -> {
			settleTurn();
		});
		timer.setRepeats(false);
		while(completed != true) {}
		timer.start();
	}
	
	private void nextTurn() {
		if(player.isAlive() == true) {
			reset();
		} else {
			setMoney("");
			setStatus("Your bank balance is smaller than the minimum bet of: " + Player.minBet + ". Ending game in 3 second");
			Timer timer = new Timer(3000, end -> {
				frame.dispose();
		        System.exit(0);
			});
			timer.setRepeats(false);
			timer.start();
		}
	}
}
