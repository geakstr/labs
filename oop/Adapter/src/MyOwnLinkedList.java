import java.util.LinkedList;
import java.util.List;

public class MyOwnLinkedList<T> implements IMyOwnList<T> {
	private final LinkedList<T> list;
	
	public MyOwnLinkedList() {
		this.list = new LinkedList<>();
	}
	
	public void push(final T x) {
		list.add(x);
	}
	
	public void push(final List<T> x) {
		list.addAll(x);
	}
	
	public boolean has(final T x) {
		return list.contains(x);
	}
	
	public void deleteAll() {
		list.clear();
	}
	
	public void delete(final int i) {
		list.remove(i);
	}
	
	public int length() {
		return list.size();
	}
	
	public T getHead() {
		return list.getFirst();
	}
	
	public T getTail() {
		return list.getLast();
	}
	
	public String toString() {
		return list.toString();
	}
}
