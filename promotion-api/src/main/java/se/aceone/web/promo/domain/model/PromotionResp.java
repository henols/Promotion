package se.aceone.web.promo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = PromotionResp.Builder.class)

public class PromotionResp {

	private PromotonState state;
	private Discount discount;

	public PromotonState getState() {
		return state;
	}

	public Discount getDiscount() {
		return discount;
	}

	private PromotionResp(Builder builder) {
		this.state = builder.state;
		this.discount = builder.discount;
	}
	

	@Override
	public String toString() {
		return "PromotionResp [state=" + state + ", discount=" + discount + "]";
	}

	/**
	 * Creates builder to build {@link PromotionResp}.
	 * 
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link PromotionResp}.
	 */
	public static final class Builder {
		private PromotonState state;
		private Discount discount;

		private Builder() {
		}

		public Builder withState(PromotonState state) {
			this.state = state;
			return this;
		}

		public Builder withDiscount(Discount discount) {
			this.discount = discount;
			return this;
		}

		public PromotionResp build() {
			return new PromotionResp(this);
		}
	}

}
