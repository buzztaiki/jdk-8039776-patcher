package com.github.buzztaiki.jdk8039776;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class PatchTransfomer implements ClassFileTransformer {

    @Override
    public byte[] transform(
        ClassLoader loader, String className,
        Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
        byte[] classfileBuffer
    ) throws IllegalClassFormatException {
        if (className.equals("java/beans/Introspector")) {
            try {
                return transformIntrospector(classfileBuffer);
            } catch (IOException | NotFoundException | CannotCompileException | RuntimeException e) {
                System.err.println(Name.get() + " ERROR:");
                e.printStackTrace(System.err);
                return null;
            }
        }
        return null;
    }

    // http://hg.openjdk.java.net/jdk8u/jdk8u/jdk/rev/a7124a687f8a
    //    private static boolean isAssignable(Class<?> current, Class<?> candidate) {
    //-        return current == null ? candidate == null : current.isAssignableFrom(candidate);
    //+        return ((current == null) || (candidate == null)) ? current == candidate : current.isAssignableFrom(candidate);
    //    }
    private byte[] transformIntrospector(byte[] classfileBuffer) throws IOException, NotFoundException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cls = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
        try {
            CtMethod method = cls.getDeclaredMethod("isAssignable", pool.get(new String[]{"java.lang.Class", "java.lang.Class"}));
            if (method == null) {
                throw new NullPointerException("Could not find method isAssignable");
            }
            method.setBody("return (($1 == null) || ($2 == null)) ? $1 == $2 : $1.isAssignableFrom($2);");
            return cls.toBytecode();
        } finally {
            cls.detach();
        }
    }
}
