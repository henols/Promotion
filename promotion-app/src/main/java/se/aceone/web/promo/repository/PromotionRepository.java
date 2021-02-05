package se.aceone.web.promo.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import se.aceone.web.promo.domain.model.Promotion;

public interface PromotionRepository extends MongoRepository<Promotion, String> {

	@Query("{'code' : ?0, 'businessUnit' : ?1, 'enabled': true, 'start': {'$lt' : ?2 }, 'end': { '$gte' : ?2}}")
//			+ ",'$gte' : {'start': new Date(?2) "
//	+ "\"start\" :	{\"$gte\" : ISODate(\"2014-07-02T00:00:00Z\"),\"$lt\" : ISODate(\"2019-07-03T00:00:00Z\")}}")
	Promotion findValid(String promoCode, String businessUnit, LocalDate date);

}
