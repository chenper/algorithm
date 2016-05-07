import java.util.Iterator;


public class Stack<Item> implements Iterator<Item>{
	private Node first;
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
	public void push(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
	}
	public Item pop() {
		Item item = first.item;
		first = first.next;
		--N;
		return item;
	}
}
