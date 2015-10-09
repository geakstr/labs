import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		final IMyOwnList<Integer> a = new MyOwnLinkedList<>();
		
		a.push(1);
		a.push(2);
		System.out.println(a);
		
		a.push(Arrays.asList(3, 4, 5));
		System.out.println(a);
		
		System.out.println(a.has(2) + " " + a.has(10));
		
		a.delete(0);
		System.out.println(a);
		
		System.out.println(a.length());
		
		System.out.println(a.getHead());
		System.out.println(a.getTail());
		
		a.deleteAll();
		System.out.println(a);
	}

}
