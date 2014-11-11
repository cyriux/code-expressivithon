package expressivathon;

import static expressivathon.NoVatPrice.priceWithNoVat;
import static expressivathon.PriceAndVatBuilder.priceWithVat;
import static expressivathon.ProfitBuilder.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProfitCalculationTest {

	// Fluent version
	@Test
	public void acceptance_criteria_fluent() {
		given()
			.aBuy(priceWithVat(1000).atVatRate(5.00))
		.and()
			.aSell(priceWithVat(2000).atVatRate(10.00))
		.then()
			.profit().isEqualTo(priceWithNoVat(866));
	}

	@Test
	public void acceptance_criteria_initial() {
		assertEquals(866, profit(1000, 0.05, 2000, 0.10));
	}

	@Test
	public void no_vat() {
		assertEquals(1000, profit(1000, 0, 2000, 0));
	}

	@Test
	public void no_vat_bis() {
		assertEquals(2000, profit(1000, 0, 3000, 0));
	}

	@Test
	public void single_vat() {
		assertEquals((int) (1000 / (1 + 0.10)), profit(1000, 0.10, 2000, 0.10));
	}

	// Initial TDD implementation
	private int profit(int buyPrice, double buyVat, int sellPrice, double sellVat) {
		return (int) Math.round(toPriceWithoutVAT(sellPrice, sellVat) - toPriceWithoutVAT(buyPrice, buyVat));
	}

	private double toPriceWithoutVAT(int price, double vat) {
		return price / (1 + vat);
	}

}
