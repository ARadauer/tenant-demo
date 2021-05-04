package com.radauer.demo.tenant;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * Servlet Filter for reading a tenant url parameter storing it ito a {@link TenantHolder}
 */
@Component
public class TenantFilter implements Filter
{

  public static String TENANT_PARAMETER_NAME = "tenant";
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest req = (HttpServletRequest) request;
    TenantHolder.setTenant(req.getParameter(TENANT_PARAMETER_NAME));
    chain.doFilter(request, response);
  }
}
