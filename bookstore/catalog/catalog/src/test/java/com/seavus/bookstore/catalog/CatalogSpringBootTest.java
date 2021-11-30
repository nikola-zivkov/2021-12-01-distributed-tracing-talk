/*
 * Copyright (c) 2021 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */
package com.seavus.bookstore.catalog;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public @interface CatalogSpringBootTest {

}
