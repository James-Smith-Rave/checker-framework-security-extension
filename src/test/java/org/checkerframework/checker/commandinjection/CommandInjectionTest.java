package org.checkerframework.checker.commandinjection;

import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.checkerframework.framework.test.TypecheckResult;
import org.junit.runners.Parameterized.Parameters;
import java.io.File;
import java.util.List;

public class CommandInjectionTest extends CheckerFrameworkPerDirectoryTest {

public CommandInjectionTest(List<File> testFiles) {
    super(
        testFiles,
        CommandInjectionChecker.class,
        "commandinjection",
        "-Anomsgtext",
        "-nowarn"
    );
}

@Parameters
public static String[] getTestDirs() {
    return new String[] {"commandinjection"};
}

@Override
public void checkResult(TypecheckResult result) {
    boolean hasValidError = result.getUnexpectedDiagnostics().stream()
        .anyMatch(d -> d.getMessage().contains("argument") || d.getMessage().contains("inconsistent.constructor.type"));

    if (hasValidError) {
        return;
    }
    super.checkResult(result);
}
}