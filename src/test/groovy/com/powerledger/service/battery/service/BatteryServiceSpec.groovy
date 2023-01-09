package com.powerledger.service.battery.service

import com.powerledger.service.battery.model.Capacity
import com.powerledger.service.battery.model.PostalCode
import com.powerledger.service.battery.rest.BatteryInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

@DataJpaTest
class BatteryServiceSpec extends Specification {

  private BatteryService service;

  @Autowired
  BatteryRepository repo

  def setup() {
    service = new BatteryService(repo)
  }

  def "create null battery list will throw IllegalArgumentException"() {
    when:
      service.create(null)

    then:
      def ex = thrown(IllegalArgumentException)
  }

  def "get capacity information when postCodeFrom is null will throw IllegalArgumentException" () {
    when:
      service.getCapacityInformation(null, PostalCode.of(4999))

    then:
      thrown(IllegalArgumentException)
  }

  def "get capacity information when postCodeTo is null will throw IllegalArgumentException" () {
    when:
      service.getCapacityInformation(PostalCode.of(4000), null)

    then:
      thrown(IllegalArgumentException)
  }

  def "get capacity info when postCodeFrom is bigger than postCodeTo will throw IllegalArgumentException" () {
    when:
    service.getCapacityInformation(PostalCode.of(4999), PostalCode.of(4000))

    then:
    thrown(IllegalArgumentException)
  }

  def "save battery list"() {
    given:
      List<BatteryInfo> batteryInfo = Arrays.asList(BatteryInfo.builder()
          .name("Bowen Hills")
          .postCode(new PostalCode(4006))
          .capacity(new Capacity(500))
          .build())

    when:
      def created = service.create(batteryInfo)

    then:
      created != null
      created.size() == 1
      created.get(0).getId() == 1
      created.get(0).getName() == "Bowen Hills"
      created.get(0).getPostCode().getPostCode() == 4006
      created.get(0).getCapacity().getAmount() == 500

  }

  def "get capacity information"() {
    given:
      List<BatteryInfo> batteryList = Arrays.asList(
          BatteryInfo.builder()
              .name("Bowen Hills")
              .postCode(PostalCode.of(4006))
              .capacity(new Capacity(24))
              .build(),
          BatteryInfo.builder()
              .name("Virginia")
              .postCode(PostalCode.of(4014))
              .capacity(new Capacity(100))
              .build(),
          BatteryInfo.builder()
              .name("Belmont")
              .postCode(PostalCode.of(4153))
              .capacity(new Capacity(230))
              .build(),
          BatteryInfo.builder()
              .name("Bulimba")
              .postCode(PostalCode.of(4171))
              .capacity(new Capacity(300))
              .build()
      )

    when:
      service.create(batteryList)
      def info = service.getCapacityInformation(PostalCode.of(4000), PostalCode.of(4999))

    then:
      info != null

  }
}
