package org.checkerframework.checker.commandinjection.qual;

import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.SubtypeOf;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@DefaultQualifierInHierarchy
@SubtypeOf({UntaintedCommand.class})
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface TaintedCommand {}