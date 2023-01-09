package com.powerledger.service.battery.model;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * The {@code PostalCode} class represents an Australian post code.
 */
@Embeddable
@Getter
public class PostalCode {
  public static final int MIN_VALUE = 0;
  public static final int MAX_VALUE = 9999;

  public static final String CODE_OUT_OF_RANGE_MSG =
          String.format("Postal code must be between %s and %s", MIN_VALUE, MAX_VALUE);

  public static PostalCode of(int number) {
    return new PostalCode(number);
  }

  private int postCode;

  public PostalCode() {
    this(0);
  }

  public PostalCode(int number) {
    validate(number);
    postCode = number;
  }

  public void setPostCode(int number) {
    validate(number);
    postCode = number;
  }

  @Override
  public String toString() {
    return String.format("%04d", postCode);
  }

  private void validate(int number) {
    if (number < MIN_VALUE || number > MAX_VALUE) {
      throw new IllegalArgumentException(CODE_OUT_OF_RANGE_MSG);
    }
  }
}
