package com.powerledger.service.battery.service

import com.powerledger.service.battery.model.Battery
import com.powerledger.service.battery.model.Capacity
import com.powerledger.service.battery.model.PostalCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

@DataJpaTest
class BatteryRepositorySpec extends Specification {

  @Autowired
  BatteryRepository repo

  def "save battery record"() {
    given:
      def battery = Battery.builder()
          .name("Bowen Hills")
          .postCode(new PostalCode(4006))
          .capacity(new Capacity(500))
          .build()
      def saved = repo.save(battery)

    when:
      def response = repo.findById(saved.getId())

    then:
      response.isEmpty() == false
      response.get().getName() == battery.getName()
      response.get().getPostCode().getPostCode() == battery.getPostCode().getPostCode()
      response.get().getCapacity().getAmount() == battery.getCapacity().getAmount()
  }

  def "search for batteries in postcode range"() {
    given:
      List<Battery> batteryList = Arrays.asList(
          Battery.builder()
            .name("Bowen Hills")
            .postCode(PostalCode.of(4006))
            .capacity(new Capacity(24))
            .build(),
          Battery.builder()
            .name("Virginia")
            .postCode(PostalCode.of(4014))
            .capacity(new Capacity(100))
            .build(),
          Battery.builder()
            .name("Belmont")
            .postCode(PostalCode.of(4153))
            .capacity(new Capacity(230))
            .build(),
          Battery.builder()
            .name("Bulimba")
            .postCode(PostalCode.of(4171))
            .capacity(new Capacity(300))
            .build()
      )
      repo.saveAll(batteryList)

    when:
      def found = repo.findAllByPostCodeBetween(PostalCode.of(4000), PostalCode.of(4999))

    then:
      found != null
      found.size() == 4
  }
}
