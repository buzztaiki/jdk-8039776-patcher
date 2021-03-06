package com.github.buzztaiki.jdk8039776;

import java.lang.instrument.Instrumentation;

public class PatchAgent {
    public static void premain(String args, Instrumentation inst) throws Exception {
        if (new TargetVersion().matches(System.getProperties())) {
            System.err.println(Name.get() + " Loaded");
            inst.addTransformer(new PatchTransfomer());
        }
    }
}
