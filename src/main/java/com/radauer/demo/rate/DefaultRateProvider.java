package com.radauer.demo.rate;

import java.math.BigDecimal;

import com.radauer.demo.tenant.TenantService;

@TenantService
public class DefaultRateProvider implements RateProvider
{
  @Override
  public BigDecimal getRate()
  {
    return BigDecimal.ZERO;
  }
}
