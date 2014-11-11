package expressivathon;

public class WithVatPrice {

	private final double price;

	public WithVatPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public NoVatPrice withoutVat(double vatRate) {
		return new NoVatPrice(price / (1 + vatRate));
	}

}
