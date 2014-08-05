package com.github.buzztaiki.jdk8039776;

import java.lang.instrument.Instrumentation;

public class PatchAgent {
    public static void premain(String args, Instrumentation inst) throws Exception {
        inst.addTransformer(new PatchTransfomer());
    }
}
