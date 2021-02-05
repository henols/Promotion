package se.aceone.web.promo.domain.model;

/**
 * @author henrik
 *
 */
public class AmountDiscount implements Discount {
	private double amount;

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "AmountDiscount [amount=" + amount + "]";
	}
	
	
}
