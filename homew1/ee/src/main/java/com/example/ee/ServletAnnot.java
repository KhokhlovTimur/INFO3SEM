package com.example.ee;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ServletAnnot {
    public String servletURL() default "";
    public String pathToResources() default "";
    public String someInfo()default "";
}
