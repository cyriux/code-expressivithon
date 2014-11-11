package expressivathon;

public class PriceAndVatBuilder {
	private final int price;
	private double vat;

	public static PriceAndVatBuilder priceWithVat(int price) {
		return new PriceAndVatBuilder(price);
	}

	public PriceAndVatBuilder(int price) {
		this.price = price;
	}

	public PriceAndVatBuilder atVatRate(double vatPercent) {
		this.vat = vatPercent / 100.;
		return this;
	}

	public WithVatPrice getPrice() {
		return new WithVatPrice(price);
	}

	public double getVat() {
		return vat;
	}

	@Override
	public String toString() {
		return "price with Vat: " + price + " at Vat rate: " + vat;
	}
}
