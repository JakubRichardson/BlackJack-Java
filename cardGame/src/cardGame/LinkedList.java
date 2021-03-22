package cardGame;

public class LinkedList<x> {
	Node<x> head;
	int length;
	
	LinkedList() {
		length = 0;
	}
	
	public void append(x X) {
		if(head == null) {
			head = new Node<x>(X);
		} else {
			Node<x> tail = findTail();
			tail.next = new Node<x>(X);
		}
		length++;
	}
	
	public Node<x> findTail() {
		Node<x> node = head;
		if(node != null) {
			while(node.next != null) {
				node = node.next;
			}
		}
		return node;
	}
	
	public void clear() {
		head = null;
		length = 0;
	}
}
