package com.radauer.demo.greeting;

import com.radauer.demo.tenant.TenantService;

@TenantService(tenant = "HU")
public class GreetingServiceHU implements GreetingService
{

  @Override
  public String getGreeting()
  {
    return "Helló Világ!";
  }
}
