package com.powerledger.service.battery.model

import spock.lang.Specification

class CapacitySpec extends Specification{

  def "throw IllegalArgumentException when value out of range"() {
    when:
      def capacity = new Capacity(-1)

    then:
      capacity == null
      def ex = thrown(IllegalArgumentException)
      ex.getMessage() == Capacity.OUT_OF_RANGE_MSG
  }
}
