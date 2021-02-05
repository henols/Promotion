package se.aceone.web.promo.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import se.aceone.web.promo.domain.model.Promotion;
import se.aceone.web.promo.domain.model.PromotionResp;

public interface PromotionService {

	PromotionResp validatePromotion(String code, String cust, String unit);

	PromotionResp comfirmPromotion(String code, String cust, String unit);

	List<Promotion> all();

	Promotion create(@RequestBody Promotion promotion);

	Promotion one(String id);

	Promotion update(Promotion promotion, @PathVariable String id);

	void delete(String id);

}
