package com.powerledger.service.battery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

  private String name;

  @Embedded
  private PostalCode postCode;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column = @Column(name = "capacity"))
  })
  private Capacity capacity;
}
