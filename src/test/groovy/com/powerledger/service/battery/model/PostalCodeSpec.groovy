package com.powerledger.service.battery.model

import spock.lang.Specification

class PostalCodeSpec extends Specification {

  def "throw IllegalArgumentException when value out of range"() {
    when:
      def postCode = new PostalCode(12345)

    then:
      postCode == null
      def ex = thrown(IllegalArgumentException)
      ex.getMessage() == PostalCode.CODE_OUT_OF_RANGE_MSG
  }
}
