package cardGame;

public class Dealer extends Person{
	
	
	Dealer() {
		name = 'D';
		y = 55;
	}
	
	public String getDealScore() {
		int total = 0;
		boolean aceFlag = false;
		int value = hand.head.data.getValue();
		if(value > 10) {
			value = 10;
		}else if(value == 1) {
			aceFlag = true;
		}
		total += value;
		String str = Integer.toString(total);
		if(aceFlag && total + 10 <= 21) {
			str += "/" + Integer.toString(total+10);
		}
		return str;
	}
}
