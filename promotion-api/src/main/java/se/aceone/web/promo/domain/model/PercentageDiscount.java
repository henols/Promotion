package se.aceone.web.promo.domain.model;

public class PercentageDiscount implements Discount {
	private int percentage;

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "PercentageDiscount [percentage=" + percentage + "]";
	}
}
