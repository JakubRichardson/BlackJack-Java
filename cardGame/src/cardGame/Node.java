package cardGame;

public class Node<x> {
	x data; 
	Node<x> next;
	Node(x card) {
		this.data = card;
		this.next = null;
	}
}
