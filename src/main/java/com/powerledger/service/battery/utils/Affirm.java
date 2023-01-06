package com.powerledger.service.battery.utils;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Utility class to assists in validating arguments that are objects.
 */
public class Affirm {
  private Optional<Object> optional;

  public static Affirm of(Object obj) {
    return new Affirm(obj);
  }

  private Affirm(Object obj) {
    optional = Optional.ofNullable(obj);
  }

  /**
   * Check that object is {@code null}.
   * <pre>
   *     Validator.of(value).isNull("The value must be null");
   * </pre>
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if object is not {@code null}
   */
  public void isNull(String message) {
    if (optional.isPresent()) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Check that an object is {@code null}.
   * <pre>
   *     Validator.of(value).isNull(() -&gt; "The value'" + value + "' must be null");
   * </pre>
   * @param messageSupplier a supplier for the exception message to use if object is not null
   * @throws IllegalArgumentException if object is not {@code null}
   */
  public void isNull(Supplier<String> messageSupplier) {
    if (optional.isPresent()) {
      new IllegalArgumentException(nullSafeGet(messageSupplier));
    }
  }
  /**
   * Affirm that the object is not {@code null}
   * <pre>
   *  Validator.of(value).notNull("The value must not be null");
   * </pre>
   * @param message
   */
  public void notNull(String message) {
    String str = Optional.ofNullable(message)
        .filter(s -> !s.isEmpty())
        .orElse("Argument must not be null");
    optional.orElseThrow(() -> new IllegalArgumentException(str));
  }

  /**
   * Affirm that the object is not {@code null}.
   * <pre>
   *     Affirm.of(clazz).notNull(() -@gt; "The class '" + clazz.getName() + "' must not be null");
   * </pre>
   * @param messageSupplier a supplier for the exception message
   * @throws IllegalArgumentException if the object is {@code null}
   */
  public void notNull(Supplier<String> messageSupplier) {
    optional.orElseThrow(() -> new IllegalArgumentException(nullSafeGet(messageSupplier)));
  }

  private String nullSafeGet(Supplier<String> messageSupplier) {
    return (messageSupplier != null ? messageSupplier.get() : null);
  }
}
