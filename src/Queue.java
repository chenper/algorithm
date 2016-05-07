import java.util.Iterator;

public class Queue<Item> implements Iterator<Item>{
	private Node first;
	private Node last;
	private int N;
	private class Node{
		Item item;
		Node next;
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) 
			first = last;
		else
			oldlast.next = last;
		++N;
	}
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		--N;
		if(isEmpty())
			last = null;
		return item;
	}
}
