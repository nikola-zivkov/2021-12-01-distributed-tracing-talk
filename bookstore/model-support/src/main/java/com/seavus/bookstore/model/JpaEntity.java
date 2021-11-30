package com.seavus.bookstore.model;

import lombok.AccessLevel;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class JpaEntity {

  @Getter
  @Id
  @GeneratedValue(generator = "entity-uuid")
  @GenericGenerator(name = "entity-uuid", strategy = "com.seavus.bookstore.model.JpaEntityUuidGenerator")
  private String uuid;

  @Getter(AccessLevel.PACKAGE)
  @Transient
  private String assignedUuid;

  public void assignUuid(String uuid) {
    this.assignedUuid = uuid;
  }
}
