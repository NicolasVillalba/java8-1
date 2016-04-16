package bq.java8.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
// Must be annotation class which value is Filter[]
@Repeatable(Filters.class)
public @interface Filter {
	
	String value();
	
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Filters{
	Filter[] value();
}
