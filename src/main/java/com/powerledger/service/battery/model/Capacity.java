package com.powerledger.service.battery.model;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * The {@code Capacity} class represents the maximum amount of energy a battery can contain in Watts.
 * </b>
 * The capacity of a battery is bigger than zero.
 */
@Embeddable
@Getter
public class Capacity {

  public static final String OUT_OF_RANGE_MSG = "Capacity must be positive";

  private int amount;

  public Capacity() {
    this(0);
  }

  public Capacity(int amount) {
    validate(amount);
    this.amount = amount;
  }

  public void setAmount(int amount) {
    validate(amount);
    this.amount = amount;
  }

  private void validate(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
  }
}
