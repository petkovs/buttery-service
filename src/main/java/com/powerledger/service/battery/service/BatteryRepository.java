package com.powerledger.service.battery.service;

import com.powerledger.service.battery.model.Battery;
import com.powerledger.service.battery.model.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

  List<Battery> findAllByPostCodeBetween(PostalCode start, PostalCode end);
}
