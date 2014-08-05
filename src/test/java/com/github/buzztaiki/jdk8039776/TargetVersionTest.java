package com.github.buzztaiki.jdk8039776;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Properties;

import org.junit.Test;


public class TargetVersionTest {
    private static TargetVersion target() {
        return new TargetVersion();
    }

    @Test public void vendorMatchesOracle() {
        assertThat(target().vendorMatches("Oracle Corporation"), is(true));
    }

    @Test public void vendorNotMatchesIBM() {
        assertThat(target().vendorMatches("IBM"), is(false));
    }

    @Test public void versionMatches1_8_0_11() {
        assertThat(target().versionMatches("1.8.0_11"), is(true));
    }

    @Test public void versionMatches1_8_0_05() {
        assertThat(target().versionMatches("1.8.0_05"), is(true));
    }

    @Test public void versionNotMatches1_8_0_20() {
        assertThat(target().versionMatches("1.8.0_20"), is(false));
    }

    @Test public void versionNotMatches1_7_0_11() {
        assertThat(target().versionMatches("1.7.0_11"), is(false));
    }

    @Test public void matchesOracle1_8_0_11() {
        Properties props = new Properties();
        props.setProperty("java.vendor", "Oracle Corporation");
        props.setProperty("java.version", "1.8.0_11");
        assertThat(target().matches(props), is(true));
    }

    @Test public void notMatchesOracle1_8_0_20() {
        Properties props = new Properties();
        props.setProperty("java.vendor", "Oracle Corporation");
        props.setProperty("java.version", "1.8.0_20");
        assertThat(target().matches(props), is(false));
    }

    @Test public void notMatchesEmptyProps() {
        Properties props = new Properties();
        assertThat(target().matches(props), is(false));
    }
}
