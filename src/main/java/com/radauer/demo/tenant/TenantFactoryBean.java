package com.radauer.demo.tenant;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Creates a bean for a given Class and wraps it into a {@link TenantProxy}
 * @param <T> Type of the given Class
 */
public class TenantFactoryBean<T> extends AbstractFactoryBean<T>
{
  private final TenantProxy tenantProxy;

  public final Class<T> clazz;

  public TenantFactoryBean(TenantProxy tenantProxy, Class<T> clazz)
  {
    this.tenantProxy = tenantProxy;
    this.clazz = clazz;
  }

  @Override
  public Class<T> getObjectType()
  {
    return clazz;
  }

  @Override
  protected T createInstance()
  {
    return (T) Proxy.newProxyInstance(TenantFactoryBean.class.getClassLoader(),
      new Class[]{clazz}, tenantProxy);
  }
}
