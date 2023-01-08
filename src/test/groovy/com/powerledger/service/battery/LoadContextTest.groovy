package com.powerledger.service.battery

import com.powerledger.service.battery.rest.BatteryController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

  @Autowired (required = false)
  private BatteryController batteryController

  def "when context is loaded then all expected beans are created"() {
    expect: "the BatteryController is created"
    batteryController
  }
}
