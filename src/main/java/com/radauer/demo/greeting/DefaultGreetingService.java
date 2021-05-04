package com.radauer.demo.greeting;

import com.radauer.demo.tenant.TenantService;

@TenantService
public class DefaultGreetingService implements GreetingService
{
  public String getGreeting()
  {
    return "Hello World!";
  }
}
