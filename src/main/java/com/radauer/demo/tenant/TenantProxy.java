package com.radauer.demo.tenant;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class TenantProxy implements InvocationHandler, ApplicationContextAware
{

  private final Map<String, String> beanNameTenantCache = new HashMap<>();
  private ApplicationContext applicationContext;

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
  {
    Object newObject = getBean(method.getDeclaringClass());
    return method.invoke(newObject, args);
  }

  public <T> T getBean(Class<T> clazz)
  {
    String tenant = TenantHolder.getTenant();

    Map<String, T> beans = applicationContext.getBeansOfType(clazz);

    T defaultBean = null;
    for (String beanName : beans.keySet())
    {
      String tenantOfBean = getTenant(beanName);

      if (tenantOfBean == null)
      {
        continue;
      }

      T bean = beans.get(beanName);
      if (tenantOfBean.equals(TenantService.DEFAULT_TENANT))
      {
        if (ObjectUtils.isEmpty(tenant))
        {
          return bean;
        }
        defaultBean = bean;
      }
      if (tenantOfBean.equalsIgnoreCase(tenant))
      {
        return bean;
      }
    }
    return defaultBean;
  }

  public String getTenant(String beanName)
  {
    if (!beanNameTenantCache.containsKey(beanName))
    {
      TenantService tenantService = applicationContext.findAnnotationOnBean(beanName, TenantService.class);
      if (tenantService == null)
      {
        return null;
      }
      beanNameTenantCache.put(beanName, tenantService.tenant());
    }
    return beanNameTenantCache.get(beanName);

  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
  {
    this.applicationContext = applicationContext;
  }
}
