package me.rxsto.epsilon.core.commands.registry;

import me.rxsto.epsilon.core.commands.CommandCategory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandRegistry {

    String name();

    CommandCategory category() default CommandCategory.GENERAL;

    String actionText() default "";
}
