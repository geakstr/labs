import java.util.List;


public interface IMyOwnList<T> {
	void push(T x);
	void push(List<T> x);
	boolean has(T x);
	void deleteAll();
	void delete(int i);
	int length();
	T getHead();
	T getTail();
}
