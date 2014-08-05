package com.github.buzztaiki.jdk8039776;

import java.util.Properties;

public class TargetVersion {
    public boolean vendorMatches(String vendor) {
        return vendor.equals("Oracle Corporation");
    }

    public boolean versionMatches(String version) {
        String[] a = version.split("_");
        if (a.length != 2) return false;
        if (!a[0].equals("1.8.0")) return false;

        try {
            return Integer.parseInt(a[1]) <= 11;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean matches(Properties props) {
        return vendorMatches(props.getProperty("java.vendor", ""))
            && versionMatches(props.getProperty("java.version", ""));
    }
}
