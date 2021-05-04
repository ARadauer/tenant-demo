package com.radauer.demo.tenant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface TenantService
{
  String DEFAULT_TENANT = "default";

  String tenant() default DEFAULT_TENANT;
}
