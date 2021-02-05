package se.aceone.web.promo;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se.aceone.web.promo.domain.model.AmountDiscount;
import se.aceone.web.promo.domain.model.DiscountType;
import se.aceone.web.promo.domain.model.PercentageDiscount;
import se.aceone.web.promo.domain.model.Promotion;
import se.aceone.web.promo.domain.model.PromotionResp;
import se.aceone.web.promo.repository.PromotionRepository;
import se.aceone.web.promo.service.PromotionService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	PromotionRepository repository;
	
	@Autowired
	PromotionService service;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		repository.deleteAll();
//
//		LocalDate date = LocalDate.now();
//		Promotion promoAmount = new Promotion();
//		promoAmount.setCode("TestAmount");
//		promoAmount.setBusinessUnit(Arrays.asList("SHOP"));
//		promoAmount.setStart(date.minusDays(1));
//		promoAmount.setEnd(date.plusMonths(1));
//		promoAmount.setEnabled(true);
//		AmountDiscount discount = new AmountDiscount();
//		discount.setAmount(25);
//		promoAmount.setType(DiscountType.ONCE_PER_CUSTOMER);
//		promoAmount.setDiscount(discount);
//		Promotion save = repository.save(promoAmount);
//		System.out.println(save.getCode());
//
//		Promotion promoProc = new Promotion();
//		promoProc.setCode("TestProc");
//		promoProc.setBusinessUnit(Arrays.asList("SHOP"));
//		promoProc.setStart(date.minusDays(1));
//		promoProc.setEnd(date.plusMonths(1));
//		promoProc.setEnabled(true);
//		promoProc.setType(DiscountType.MANY);
//		PercentageDiscount proc = new PercentageDiscount();
//		proc.setPercentage(15);
//		promoProc.setDiscount(proc);
//		save = repository.save(promoProc);
//		System.out.println(save.getCode());
//		
//		PromotionResp resp = service.validatePromotion("TestProc", "ace", "SHOP");
//		System.out.println(resp.getState());
//		resp = service.comfirmPromotion("TestProc", "ace", "SHOP");
//		System.out.println(resp.getState());
//		resp = service.comfirmPromotion("TestProc", "ace", "SHOP");
//		System.out.println(resp.getState());

	}

}
