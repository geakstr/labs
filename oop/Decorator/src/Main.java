public class Main {
	interface ICar {
		public int getPrice();
		public String getModel();
		public String getDescription();
	}
	
	static class BaseCar implements ICar {
		private final int price;
		private final String model;
		private final String description;
		
		public BaseCar(final int price, final String model, final String description) {
			this.price = price;
			this.model = model;
			this.description = description;
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
		public String getDescription() {
			return description;
		}
	}
	
	static abstract class CarDecorator implements ICar {
		protected final ICar car;
		
		public CarDecorator(final ICar car) {
			this.car = car;
		}
		
		@Override
		public int getPrice() {
			return car.getPrice();
		}
		
		@Override
		public String getModel() {
			return car.getModel();
		}
		
		@Override
		public String getDescription() {
			return car.getDescription();
		}
	}
	
	static class CarTradeDecorator extends CarDecorator {
		public CarTradeDecorator(final ICar car) {
			super(car);
		}
		
		public void sell() {
			System.out.println(car.getModel() + " sold for $" + car.getPrice());
		}
		
		public void buy() {
			System.out.println(car.getModel() + " bought for $" + car.getPrice());
		}
	}
	
	public static void main(String[] args) {
		ICar bmw = new BaseCar(350, "BMW", "First description");
		ICar audi = new BaseCar(235, "Audi", "Second description");
		
		bmw = new CarTradeDecorator(bmw);
		audi = new CarTradeDecorator(audi);
		
		((CarTradeDecorator) bmw).sell();
		((CarTradeDecorator) audi).buy();
	}
}
