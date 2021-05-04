package com.radauer.demo;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.radauer.demo.rate.RateProvider;
import com.radauer.demo.greeting.GreetingService;
import com.radauer.demo.tenant.TenantFactoryBean;
import com.radauer.demo.tenant.TenantProxy;

@SpringBootApplication
public class DemoApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  @Primary
  public FactoryBean getFeatureServiceBeanFactory(TenantProxy tenantProxy)
  {
    return new TenantFactoryBean(tenantProxy, GreetingService.class);
  }

  @Bean
  @Primary
  public FactoryBean getRateProviderBeanFactory(TenantProxy tenantProxy)
  {
    return new TenantFactoryBean(tenantProxy, RateProvider.class);
  }
}
