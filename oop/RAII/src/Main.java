public class Main {
	static interface Destructable {
		public void destruct();
	}

	static interface Copyble<T> {
		public T copy();
	}

	static class Food implements Destructable, Copyble<Food> {
		private final String title;

		public Food(final String title) {
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		@Override
		public void destruct() {
			System.out.println(title + " was destructed");
		}

		@Override
		public Food copy() {
			return new Food(title);
		}
	}

	static class Unique_Ptr<T extends Destructable & Copyble<T>> implements AutoCloseable {
		private T o;

		public Unique_Ptr(final T o) {
			this.o = o;

			System.out.println("Unique pointer init");
		}

		public void reset(final T o) {
			this.o.destruct();

			this.o = o;
		}

		public T release() {
			final T ret = o.copy();

			reset(null);

			return ret;
		}

		public T get() {
			return o;
		}

		public void swap(Unique_Ptr<T> b) {
			final T tmp = b.get().copy();
			b.set(o.copy());
			o = tmp;
		}

		private void set(T o) {
			this.o = o;
		}

		@Override
		public void close() {
			System.out.println("Unique pointer was out");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		try (Unique_Ptr a_ptr = new Unique_Ptr(new Food("Шпроты"))) {

			try (Unique_Ptr b_ptr = new Unique_Ptr(new Food("Пюре"))) {

				System.out.println("Get B : " + ((Food) b_ptr.get()).getTitle());

				System.out.println("Reset B with Гречка");
				b_ptr.reset(new Food("Гречка"));

				System.out.println("Get B : " + ((Food) b_ptr.get()).getTitle());

				System.out.println("Swap A & B");
				a_ptr.swap(b_ptr);

				System.out.println("Get A : " + ((Food) a_ptr.get()).getTitle());
				System.out.println("Get B : " + ((Food) b_ptr.get()).getTitle());

				System.out.println("Object from B after release : " + ((Food) b_ptr.release()).getTitle());

			}

		}
	}
}