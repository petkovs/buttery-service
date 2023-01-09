package com.powerledger.service.battery.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.powerledger.service.battery.model.Battery
import com.powerledger.service.battery.model.Capacity
import com.powerledger.service.battery.model.PostalCode
import com.powerledger.service.battery.service.BatteryService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import spock.lang.Specification

import static org.hamcrest.Matchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@AutoConfigureMockMvc
@WebMvcTest
class BatteryControllerSpec extends Specification {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper

  @SpringBean
  BatteryService service = Mock()

  def "create battery information records"() {
    given:
      List<BatteryInfo> batteryInfo = Arrays.asList(BatteryInfo.builder()
          .name("Bowen Hills")
          .postCode(new PostalCode(4006))
          .capacity(new Capacity(500))
          .build())
      List<BatteryInfo> response = Arrays.asList(BatteryInfo.builder()
          .id(1)
          .name("Bowen Hills")
          .postCode(new PostalCode(4006))
          .capacity(new Capacity(500))
          .build())
      def ow = mapper.writer().withDefaultPrettyPrinter()
      def rqstJson = ow.writeValueAsString(batteryInfo)

    when:
      def result = mvc.perform(post("/batteries")
          .contentType(MediaType.APPLICATION_JSON)
          .content(rqstJson)
      )

    then:
      1 * service.create(_) >> Arrays.asList(Battery.builder()
          .id(1)
          .name(batteryInfo.get(0).getName())
          .postCode(batteryInfo.get(0).getPostCode())
          .capacity(batteryInfo.get(0).getCapacity())
          .build())
      result
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(mapper.writeValueAsString(response))))
  }

  def "create empty battery info list returns empty list"() {
    given:
      def rqstJson = "[]"

    when:
      def result = mvc.perform(post("/batteries")
          .contentType(MediaType.APPLICATION_JSON)
          .content(rqstJson)
      )

    then:
      result
          .andExpect(status().isOk())
          .andExpect(content().string(containsString("[]")))

  }

  def "when postcode is not between 0 and 9999 then client error"() {
    given:
      List<BatteryInfo> batteryInfo = Arrays.asList(BatteryInfo.builder()
          .name("Bowen Hills")
          .postCode(new PostalCode(4006))
          .capacity(new Capacity(500))
          .build())
      def ow = mapper.writer().withDefaultPrettyPrinter()
      def str = ow.writeValueAsString(batteryInfo)
      def rqstJson = str.replace("4006", "12345")

    when:
      def result = mvc.perform(post("/batteries")
          .contentType(MediaType.APPLICATION_JSON)
          .content(rqstJson)
      )

    then:
      result
          .andExpect(status().is4xxClientError())
  }

  def "when capacity is negative then client error"() {
    given:
    List<BatteryInfo> batteryInfo = Arrays.asList(BatteryInfo.builder()
        .name("Bowen Hills")
        .postCode(new PostalCode(4006))
        .capacity(new Capacity(500))
        .build())
    def ow = mapper.writer().withDefaultPrettyPrinter()
    def str = ow.writeValueAsString(batteryInfo)
    def rqstJson = str.replace("500", "-1")

    when:
    def result = mvc.perform(post("/batteries")
        .contentType(MediaType.APPLICATION_JSON)
        .content(rqstJson)
    )

    then:
      result
          .andExpect(status().is4xxClientError())
  }

  def "get capacity for invalid postcode range then client error"() {
    when:
      def result = mvc.perform(get("/batteries/capacity")
          .contentType(MediaType.APPLICATION_JSON)
          .param("postCodeFrom", "4006")
          .param("postCodeTo", "12345")
      )

    then:
      def ex = thrown(org.springframework.web.util.NestedServletException)
      IllegalArgumentException cause = ex.getCause()
      cause.getMessage() == "Postal code must be between 0 and 9999"
  }

}
