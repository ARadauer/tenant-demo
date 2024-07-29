package com.radauer.demo.tenant;

/**
 * Utility Class to hold the current tenant in a ThreadLocal. the value of the ThreadLocal is bound to a request
 */
public class TenantHolder
{
    private static final ThreadLocal<String> tenant = new ThreadLocal<>();

    public static String getTenant()
    {
        return tenant.get();
    }

    public static void setTenant(String newTenant)
    {
        tenant.set(newTenant);
    }

    public static void clean()
    {
        tenant.remove();
    }
}
