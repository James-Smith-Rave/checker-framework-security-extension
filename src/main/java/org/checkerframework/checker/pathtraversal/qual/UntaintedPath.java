package org.checkerframework.checker.pathtraversal.qual;

import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TargetLocations;
import org.checkerframework.framework.qual.TypeUseLocation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@SubtypeOf({})
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@TargetLocations({TypeUseLocation.EXPLICIT_LOWER_BOUND, TypeUseLocation.EXPLICIT_UPPER_BOUND, TypeUseLocation.PARAMETER, TypeUseLocation.FIELD, TypeUseLocation.LOCAL_VARIABLE, TypeUseLocation.RESOURCE_VARIABLE, TypeUseLocation.RETURN})
public @interface UntaintedPath {}