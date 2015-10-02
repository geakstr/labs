public class Main {
	interface ICar {
		public int getPrice();

		public String getModel();
	}

	static class BaseCar implements ICar {
		private final int price;
		private final String model;

		public BaseCar(final int price, final String model) {
			this.price = price;
			this.model = model;
		}

		@Override
		public int getPrice() {
			return price;
		}

		@Override
		public String getModel() {
			return model;
		}
	}

	static class CarFactory {
		public static ICar getCar(final String model) {
			if (model == null) {
				return null;
			}

			if (model.equalsIgnoreCase("BMW")) {
				return new BaseCar(35000, "BWM");
			} else if (model.equalsIgnoreCase("Audi")) {
				return new BaseCar(25000, "Audi");
			}

			return null;
		}
	}

	public static void main(String[] args) {
		ICar bmw = CarFactory.getCar("bmw");
		ICar audi = CarFactory.getCar("audi");

		System.out.println(bmw.getModel() + " price " + bmw.getPrice());
		System.out.println(audi.getModel() + " price " + audi.getPrice());
	}
}
