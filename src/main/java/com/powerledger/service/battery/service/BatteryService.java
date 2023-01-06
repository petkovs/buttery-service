package com.powerledger.service.battery.service;

import com.powerledger.service.battery.model.Battery;
import com.powerledger.service.battery.model.PostalCode;
import com.powerledger.service.battery.rest.BatteryInfo;
import com.powerledger.service.battery.rest.CapacityInfo;
import com.powerledger.service.battery.utils.Affirm;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Service
public class BatteryService {

  private final BatteryRepository repo;

  public List<Battery> create(List<BatteryInfo> batteryList) {
    Affirm.of(batteryList).notNull("batteryList must not be null");

    return batteryList.stream()
        .map(binfo -> repo.save(binfo.toBattery()))
        .collect(Collectors.toList());
  }

  public CapacityInfo getCapacityInformation(PostalCode postCodeFrom, PostalCode postCodeTo) {
    Affirm.of(postCodeFrom).notNull("postCodeFrom must not be null");
    Affirm.of(postCodeTo).notNull("postCodeTo must not be null");

    if (postCodeTo.getPostCode() < postCodeFrom.getPostCode()) {
      throw new IllegalArgumentException(String.format("postCodeFrom %d must be bigger that postCodeTo %d", postCodeFrom, postCodeTo));
    }
    List<Battery> batteryList = repo.findAllByPostCodeBetween(postCodeFrom, postCodeTo);

    return CapacityInfo.builder()
        .batteryNames(batteryList.stream()
          .map(b -> b.getName())
          .sorted()
          .collect(Collectors.toList())
        )
        .total(batteryList.stream()
          .map(b -> b.getCapacity().getAmount())
          .mapToLong(Integer::longValue)
          .sum()
        )
        .average(batteryList.stream()
          .map(b -> b.getCapacity().getAmount())
          .mapToDouble(a -> a)
          .average()
          .orElse(-1)
        )
        .build();
  }
}
