package com.powerledger.service.battery.rest;

import com.powerledger.service.battery.model.Battery;
import com.powerledger.service.battery.model.Capacity;
import com.powerledger.service.battery.model.PostalCode;
import lombok.Value;

@Value
public class BatteryInfo {
  private String name;
  private PostalCode postCode;
  private Capacity capacity;

  public Battery toBattery() {
    return Battery.builder()
      .name(name)
      .postCode(postCode)
      .capacity(capacity)
      .build();
  }
}
