package com.radauer.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radauer.demo.greeting.GreetingService;
import com.radauer.demo.rate.RateProvider;

@RestController
public class GreetingController
{
  private GreetingService greetingService;
  private RateProvider rateProvider;

  public GreetingController(GreetingService greetingService, RateProvider rateProvider)
  {
    this.greetingService = greetingService;
    this.rateProvider = rateProvider;
  }

  @GetMapping("/greeting")
  public String getGreeting()
  {
    return greetingService.getGreeting() + " " + rateProvider.getRate();
  }
}
