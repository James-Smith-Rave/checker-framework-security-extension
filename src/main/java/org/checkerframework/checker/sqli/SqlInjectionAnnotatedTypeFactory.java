package org.checkerframework.checker.sqli;

import org.checkerframework.common.basetype.BaseAnnotatedTypeFactory;
import org.checkerframework.common.basetype.BaseTypeChecker;

public class SqlInjectionAnnotatedTypeFactory extends BaseAnnotatedTypeFactory {

public SqlInjectionAnnotatedTypeFactory(BaseTypeChecker checker) {
    super(checker);
    this.postInit();
}
}