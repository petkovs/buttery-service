package com.powerledger.service.battery.model;

import javax.persistence.Embeddable;

/**
 * The {@code Capacity} class represents the maximum amount of energy a battery can contain in Watts.
 * </b>
 * The capacity of a battery is bigger than zero.
 */
@Embeddable
public class Capacity {
  private int amount;

  public Capacity() {
    this(0);
  }

  public Capacity(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    this.amount = amount;
  }
}
