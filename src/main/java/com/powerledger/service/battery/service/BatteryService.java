package com.powerledger.service.battery.service;

import com.powerledger.service.battery.model.Battery;
import com.powerledger.service.battery.rest.BatteryInfo;
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
}
