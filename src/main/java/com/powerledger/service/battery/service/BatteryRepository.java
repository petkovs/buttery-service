package com.powerledger.service.battery.service;

import com.powerledger.service.battery.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {
}
