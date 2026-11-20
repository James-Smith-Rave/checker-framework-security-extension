package org.checkerframework.checker.sqli.qual;

import org.checkerframework.framework.qual.SubtypeOf;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
* Indicates a safe, sanitized, or constant SQL string.
*/
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf({Tainted.class})
public @interface Untainted {}