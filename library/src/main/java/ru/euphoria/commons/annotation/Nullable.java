package ru.euphoria.commons.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Denotes that a parameter, field or method return value can be null.
 *
 * When decorating a method call parameter, this denotes that the parameter can
 * legitimately be null and the method will gracefully deal with it. Typically
 * used on optional parameters.
 *
 * When decorating a method, this denotes the method might legitimately return
 * null.
 *
 * This is a marker annotation and it has no specific attributes.
 *
 * @since 1.0
 */
@Retention(CLASS)
@Target({METHOD, PARAMETER, FIELD})
public @interface Nullable {
}