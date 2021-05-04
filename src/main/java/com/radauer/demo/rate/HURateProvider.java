package com.radauer.demo.rate;

import java.math.BigDecimal;
import com.radauer.demo.tenant.TenantService;

@TenantService(tenant = "HU")
public class HURateProvider extends DefaultRateProvider
{
  @Override
  public BigDecimal getRate()
  {
    return BigDecimal.TEN;
  }
}
