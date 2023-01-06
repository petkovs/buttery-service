package com.powerledger.service.battery.rest;

import com.powerledger.service.battery.model.Battery;
import com.powerledger.service.battery.model.Capacity;
import com.powerledger.service.battery.model.PostalCode;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Value
@Builder
public class BatteryInfo {
  private Long id;
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

  public static BatteryInfo from(Battery battery) {
    return Optional.ofNullable(battery)
      .map(b -> BatteryInfo.builder()
        .id(b.getId())
        .name(b.getName())
        .postCode(b.getPostCode())
        .capacity(b.getCapacity())
        .build())
      .orElse(null);
  }

  public static List<BatteryInfo> from(List<Battery> batteryList) {
    return Optional.ofNullable(batteryList).stream()
      .flatMap(Collection::stream)
      .map(BatteryInfo::from)
      .filter(Objects::nonNull)
      .collect(Collectors.toList());
  }
}
