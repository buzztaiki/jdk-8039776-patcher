package com.github.buzztaiki.jdk8039776;

public final class Name {
    public static Name get() {
        return new Name();
    }

    @Override
    public String toString() {
        return "JDK-8039776 Patch Agent";
    }
}
