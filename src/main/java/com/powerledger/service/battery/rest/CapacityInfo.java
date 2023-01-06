package com.powerledger.service.battery.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CapacityInfo {
  private List<String> batteryNames;

  @JsonProperty("totalCapacity")
  private Long total;

  @JsonProperty("averageCapacity")
  private double average;
}
