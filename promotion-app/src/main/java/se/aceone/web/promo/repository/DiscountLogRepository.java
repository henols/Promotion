package se.aceone.web.promo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import se.aceone.web.promo.domain.model.DiscountLog;

public interface DiscountLogRepository extends MongoRepository<DiscountLog, String> {

	DiscountLog findByPromoIdAndCustomerId(String promotionId, String customerId);

}
