package se.aceone.web.promo.config;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfiguration {



  @Bean
  public MongoCustomConversions customConversions() {
    List<Object> converters = new ArrayList<>();
    converters.add(new LocalDateToStringConverter());
    converters.add(new StringToLocalDateConverter());
    return new MongoCustomConversions(converters);
  }

  static class LocalDateToStringConverter implements Converter<LocalDate, String> {
    @Override
    public String convert(LocalDate localDate) {
      if (localDate == null) {
        return null;
      }
      return localDate.toString();
    }
  }

  static class StringToLocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String string) {
      if (string == null) {
        return null;
      }
      return LocalDate.parse(string);
    }
  }
}
