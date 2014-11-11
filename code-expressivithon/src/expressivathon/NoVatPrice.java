package expressivathon;

public class NoVatPrice {

	private final double price;

	public static NoVatPrice priceWithNoVat(int price) {
		return new NoVatPrice(price);
	}

	public NoVatPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public NoVatPrice profitGiven(NoVatPrice other) {
		return new NoVatPrice(price - other.price).roundClosest();
	}

	public NoVatPrice roundClosest() {
		return new NoVatPrice(Math.round(price));
	}

	@Override
	public int hashCode() {
		return (int) Double.doubleToLongBits(price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof NoVatPrice)) {
			return false;
		}
		final NoVatPrice other = (NoVatPrice) obj;
		return Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	@Override
	public String toString() {
		return "NoVatPrice=" + price;
	}

}
