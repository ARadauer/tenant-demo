package com.radauer.demo.tenant;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.Filter;
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
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            TenantHolder.setTenant(req.getParameter(TENANT_PARAMETER_NAME));
            chain.doFilter(request, response);
        }finally
        {
            TenantHolder.clean();
        }

    }
}
