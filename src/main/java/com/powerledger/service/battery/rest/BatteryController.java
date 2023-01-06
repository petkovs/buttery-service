package com.powerledger.service.battery.rest;

import com.powerledger.service.battery.model.Battery;
import com.powerledger.service.battery.model.PostalCode;
import com.powerledger.service.battery.service.BatteryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/batteries")
public class BatteryController {

  private final BatteryService service;

  /**
   * Create battery records for given list of battery info objects.
   * <br>
   * If the battery info list is empty or null, an empty list is returned.
   * @param batteryInfo a list of battery info objects
   * @return list of <code>BatteryInfo</code>.
   */
  @PostMapping
  @Operation(summary = "Create battery record for each battery info object provided")
  public List<BatteryInfo> create(@RequestBody @Valid List<BatteryInfo> batteryInfo) {
    log.info(() -> String.format("Battery info list %s", batteryInfo));

    //List<BatteryInfo> bil = BatteryInfo.from(service.create(batteryInfo));
    //return bil;
    return BatteryInfo.from(service.create(batteryInfo));
  }

  @GetMapping("/capacity")
  @Operation(summary = "Get capacity information for batteries in a post code range")
  public CapacityInfo getCapacity(@RequestParam Integer postCodeFrom, @RequestParam Integer postCodeTo) {
    return service.getCapacityInformation(new PostalCode(postCodeFrom), new PostalCode(postCodeTo));
  }
}
