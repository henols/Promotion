package se.aceone.web.promo.domain.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "promotions")
public class Promotion {

	@Id
	private String id;
//	@NotNull
//	@NotEmpty
	private String code;
//	@NotNull
	private LocalDate start;
//	@NotNull
	private LocalDate end;
//	@NotNull
	private Discount discount;
//	@NotNull
	private DiscountType type;
//	@NotNull
//	@NotEmpty
	private List<String> businessUnit;
	private boolean enabled;

	public String getCode() {
		return code;
	}

	public Discount getDiscount() {
		return discount;
	}

	public LocalDate getEnd() {
		return end;
	}

	public LocalDate getStart() {
		return start;
	}

	public DiscountType getType() {
		return type;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public void setType(DiscountType type) {
		this.type = type;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setBusinessUnit(List<String> buisnisUnit) {
		this.businessUnit = buisnisUnit;
	}

	public List<String> getBusinessUnit() {
		return businessUnit;
	}

}
