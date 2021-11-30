/*
 * Copyright (c) 2021 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */
package com.seavus.bookstore.model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

public class JpaEntityUuidGenerator extends UUIDGenerator {

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) {
    String assignedUuid = ((JpaEntity) object).getAssignedUuid();
    return assignedUuid == null ? super.generate(session, object) : assignedUuid;
  }
}
