package se.aceone.web.promo.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import se.aceone.web.promo.domain.model.DiscountLog;
import se.aceone.web.promo.domain.model.DiscountType;
import se.aceone.web.promo.domain.model.Promotion;
import se.aceone.web.promo.domain.model.PromotionResp;
import se.aceone.web.promo.domain.model.PromotonState;
import se.aceone.web.promo.exception.ObjectNotFoundException;
import se.aceone.web.promo.repository.DiscountLogRepository;
import se.aceone.web.promo.repository.PromotionRepository;

@RestController
@RequestMapping("${promotion.base-path}")
public class PromotionRestControler {

	@Autowired
	private PromotionRepository repository;

	@Autowired
	private DiscountLogRepository logRepository;

//	public Promotion getPromotion(String promotionId) {
//		Optional<Promotion> p = repository.findById(promotionId);
//		if (p.isPresent()) {
//			return p.get();
//		}
//		throw new RuntimeException("Promotion with id: " + promotionId + " not found.");
//	}

	@ApiOperation(value = "Validates a promotion", notes = "A service to validate if a promotion exists and are active.")
	@GetMapping(path = "validate", produces = APPLICATION_JSON_VALUE)
	public PromotionResp validatePromotion(@RequestParam @ApiParam(value = "Promotion code") String code,
			@RequestParam @ApiParam(value = "Customer identifier") String cust,
			@RequestParam @ApiParam(value = "Business unit") String unit) {
		LocalDate date = LocalDate.now();

		Promotion promotion = repository.findValid(code, unit, date);
		if (promotion == null) {
			return PromotionResp.builder().withState(PromotonState.UNKOWN).build();
		}

		DiscountLog log = logRepository.findByPromoIdAndCustomerId(code, cust);

		if (log != null && promotion.getType() != DiscountType.MANY) {
			return PromotionResp.builder().withState(PromotonState.CONSUMED).build();
		}

		PromotionResp resp = PromotionResp.builder().withState(PromotonState.VALID)
				.withDiscount(promotion.getDiscount()).build();

		return resp;
	}

	@ApiOperation(value = "Confirms a promotion", notes = "A service to confirm the use of a promotion.")
	@GetMapping(path = "confirm", produces = APPLICATION_JSON_VALUE)
	public PromotionResp comfirmPromotion(@RequestParam @ApiParam(value = "Promotion code") String code,
			@RequestParam @ApiParam(value = "Customer identifier") String cust,
			@RequestParam @ApiParam(value = "Business unit") String unit) {
		LocalDate date = LocalDate.now();

		Promotion promotion = repository.findValid(code, unit, date);
		if (promotion == null) {
			return PromotionResp.builder().withState(PromotonState.UNKOWN).build();
		}

		DiscountLog log = logRepository.findByPromoIdAndCustomerId(promotion.getId(), cust);

		if (log != null && promotion.getType() != DiscountType.MANY) {
			return PromotionResp.builder().withState(PromotonState.CONSUMED).build();
		}

		if (log != null && promotion.getType() != DiscountType.ONLY_ONCE) {
			promotion.setEnabled(false);
			repository.save(promotion);
		}

		if (log == null) {
			log = new DiscountLog();
			log.setCustomerId(cust);
			log.setPromoId(promotion.getId());
			log.setCount(1);
		} else {
			log.setCount(log.getCount() + 1);
		}
		logRepository.save(log);

		PromotionResp resp = PromotionResp.builder().withState(PromotonState.VALID)
				.withDiscount(promotion.getDiscount()).build();

		return resp;
	}

	@GetMapping(path = "promotions", produces = APPLICATION_JSON_VALUE)
	public List<Promotion> all() {
		return repository.findAll();
	}

	@ApiOperation(value = "Creates a new promotion")
	@PostMapping(path = "promotions", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public Promotion create(@RequestBody Promotion promotion) {
		return repository.save(promotion);
	}

	@ApiOperation(value = "Get a promotion by promotion id")
	@GetMapping("promotion/{id}")
	public Promotion one(@PathVariable @ApiParam(value = "Promotion id") String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}

	@PutMapping(path = "promotion/{id}", consumes = APPLICATION_JSON_VALUE)
	public Promotion update(@RequestBody Promotion promotion,
			@PathVariable @ApiParam(value = "Promotion id") String id) {
		return repository.findById(id).map(p -> {
			p.setCode(promotion.getCode());
			p.setBusinessUnit(promotion.getBusinessUnit());
			p.setDiscount(promotion.getDiscount());
			p.setEnabled(promotion.getEnabled());
			p.setEnd(promotion.getEnd());
			p.setStart(promotion.getStart());
			p.setType(promotion.getType());
			return repository.save(p);
		}).orElseGet(() -> {
			promotion.setId(id);
			return repository.save(promotion);
		});
	}

	@DeleteMapping("promotion/{id}")
	public void delete(@PathVariable @ApiParam(value = "Promotion id") String id) {
		repository.deleteById(id);
	}
}
