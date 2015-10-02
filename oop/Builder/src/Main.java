public class Main {
	interface ICar {
		public String getModel();

		public int getPrice();

		public int getPower();
	}

	static class Car implements ICar {
		private final String model;
		private final int price;
		private final int power;

		public Car(final Builder builder) {
			this.model = builder.model;
			this.price = builder.price;
			this.power = builder.power;
		}

		@Override
		public int getPrice() {
			return price;
		}

		@Override
		public String getModel() {
			return model;
		}

		@Override
		public int getPower() {
			return power;
		}

		public static class Builder {
			private final String model;
			private int price;
			private int power;

			public Builder(final String model) {
				this.model = model;
			}

			public Builder price(final int price) {
				this.price = price;
				return this;
			}

			public Builder power(final int power) {
				this.power = power;
				return this;
			}

			public Car build() {
				return new Car(this);
			}
		}
	}
	
	public static void main(String[] args) {
		final ICar bmw = (new Car.Builder("BMW")).price(35000).power(200).build();
		final ICar audi = (new Car.Builder("Audi")).price(25000).power(150).build();
		
		System.out.println(bmw.getModel() + " price " + bmw.getPrice() + " power " + bmw.getPower());
		System.out.println(audi.getModel() + " price " + audi.getPrice() + " power " + audi.getPower());
	}
}
