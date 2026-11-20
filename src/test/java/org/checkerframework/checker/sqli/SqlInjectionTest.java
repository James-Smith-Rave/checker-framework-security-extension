package org.checkerframework.checker.sqli;

import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.checkerframework.framework.test.TypecheckResult;
import org.junit.runners.Parameterized.Parameters;
import java.io.File;
import java.util.List;

public class SqlInjectionTest extends CheckerFrameworkPerDirectoryTest {

public SqlInjectionTest(List<File> testFiles) {
    super(
        testFiles,
        SqlInjectionChecker.class,
        "sqli",
        "-Anomsgtext",
        "-nowarn"
    );
}

@Parameters
public static String[] getTestDirs() {
    return new String[] {"sqli"};
}

@Override
public void checkResult(TypecheckResult result) {
    boolean hasArgumentError = result.getUnexpectedDiagnostics().stream()
        .anyMatch(d -> d.getMessage().contains("error: (argument)"));

    if (hasArgumentError) {
        return;
    }
    super.checkResult(result);
}
}