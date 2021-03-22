package cardGame;

public class Person {
	LinkedList<Card> hand = new LinkedList<Card>();
	char name;
	int y;

	public int getTotal() {
		int total = 0;
		boolean aceFlag = false;
		Node<Card> current = hand.head;
		while(current != null) {
			int add = current.data.getValue();
			if(add > 10) {
				add = 10;
			}else if(add == 1) {
				aceFlag = true;
			}
			total += add;
			current = current.next;
		}
		if(aceFlag && ((total + 10) <= 21)) {
			total += 10;
		}
		return total;
	}
	
	public String getTotalString() {
		int total = 0;
		boolean aceFlag = false;
		Node<Card> current = hand.head;
		while(current != null) {
			int add = current.data.getValue();
			if(add > 10) {
				add = 10;
			}else if(add == 1) {
				aceFlag = true;
			}
			total += add;
			current = current.next;
		}
		String str = Integer.toString(total);
		if(aceFlag && ((total + 10) <= 21)) {
			str += "/" + (total+10);
		}
		return str;
	}
}