package com.github.buzztaiki.jdk8039776;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Set;

import org.junit.Test;


public class PatchTest {
    public static interface SetF<X> extends Set<X> { }

    public static class Base {
        private SetF<Object> items;

        public Set<Object> getItems() {
            return items;
        }

        public void setItems(SetF<Object> items) {
            this.items = items;
        }
    }

    public static class Child extends Base {
        public Set<Object> getItems() {
            return super.getItems();
        }

        public void setItems(SetF<Object> items) {
            super.setItems(items);
        }
    }

    @Test public void baseAndObject() throws Exception {
        assertBeanInfo(Base.class, Object.class);
    }

    @Test public void childAndBase() throws Exception {
        assertBeanInfo(Child.class, Base.class);
    }

    @Test public void childAndObject() throws Exception {
        assertBeanInfo(Child.class, Object.class);
    }

    private void assertBeanInfo(Class<? extends Base> beanClass, Class<?> stopClass) throws IntrospectionException {
        PropertyDescriptor[] props = Introspector.getBeanInfo(beanClass, stopClass, Introspector.USE_ALL_BEANINFO).getPropertyDescriptors();
        assertThat(props.length, is(1));
        assertThat(props[0].getName(), is("items"));
        assertThat(props[0].getPropertyType(), equalTo(Set.class));
        assertThat(props[0].getReadMethod().getReturnType(), equalTo(Set.class));
        assertThat(props[0].getWriteMethod().getParameterTypes(), is(new Class<?>[]{SetF.class}));
    }
}
