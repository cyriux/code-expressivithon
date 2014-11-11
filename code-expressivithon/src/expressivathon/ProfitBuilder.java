package expressivathon;

import org.junit.Assert;

public class ProfitBuilder {

	private PriceAndVatBuilder buySide;
	private PriceAndVatBuilder sellSide;

	private NoVatPrice profit;

	private final StringBuilder journal = new StringBuilder();

	private ProfitBuilder() {
		journal.append("\nGiven ");
	}

	public static ProfitBuilder given() {
		return new ProfitBuilder();
	}

	public ProfitBuilder and() {
		journal.append("\nAnd ");
		return this;
	}

	public ProfitBuilder when() {
		journal.append("\nWhen ");
		return this;
	}

	public ProfitBuilder then() {
		journal.append("\nThen ");
		return this;
	}

	public ProfitBuilder aBuy(PriceAndVatBuilder buySide) {
		this.buySide = buySide;
		journal.append("a Buy " + buySide);
		return this;
	}

	public ProfitBuilder aSell(PriceAndVatBuilder sellSide) {
		this.sellSide = sellSide;
		journal.append("a Sell " + sellSide);
		return this;
	}

	public ProfitBuilder profit() {
		profit = profit(buySide.getPrice(), buySide.getVat(), sellSide.getPrice(), sellSide.getVat());
		journal.append("profit ");
		return this;
	}

	private NoVatPrice profit(WithVatPrice buyPrice, double buyVat, WithVatPrice sellPrice, double sellVat) {
		return sellPrice.withoutVat(sellVat).profitGiven(buyPrice.withoutVat(buyVat));
	}

	public String journal() {
		return journal.toString();
	}

	public void isEqualTo(NoVatPrice expected) {
		journal.append("is equal to " + expected);
		if (!profit.equals(expected)) {
			System.err.println(journal() + "\nKO: expected: " + expected);
			Assert.assertEquals("Actual profit does not match the expected profit", expected, profit);
		}
		System.out.println(journal() + "\nOK");
	}
}
