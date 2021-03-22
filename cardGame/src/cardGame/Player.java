package cardGame;

public class Player extends Person {
	private int bank = 2500;
	private int bet = 0;
	static final int minBet = 200; // set min bet
	
	Player() {
		name = 'P';
		y = 320;
	}
	
	public boolean setBet(int money) {
		if(money < minBet) { 
			return false;
		}else if (money <= bank) {
			bet = money;
			bank -= money;
			return true;
		} else {
			return false;
		}
	}
	
	public void lose() {
		bet = 0;
	}
	
	public void push() {
		bank += bet;
		bet = 0;
	}
	
	public void win() {
		bank += bet * 2;
		bet = 0;
	}
	
	public void blackJack() {
		bank += (int)(bet*2.5);
		bet = 0;
	}
	
	public String getBankString() {
		return Integer.toString(bank);
	}
	
	public String getBetString() {
		return Integer.toString(bet);
	}
	
	public String getWinBetString() {
		return Integer.toString(bet*2);
	}
	
	public String getBlackjackString() {
		return Integer.toString((int)(bet*2.5));
	}
	
	public boolean isAlive() {
		if(bank < minBet) {
			return false;
		}
		return true;
	}
}
