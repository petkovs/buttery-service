package com.powerledger.service.battery.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The {@code Battery} class represents a battery with a given name, capacity and located at a given postal code.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
@Entity
@Table(name = "battery")
public class Battery {

  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  @NonNull
  private String name;

  @NotNull
  @NonNull
  @Embedded
  private PostalCode postCode;

  @NotNull
  @NonNull
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column = @Column(name = "capacity"))
  })
  private Capacity capacity;
}
