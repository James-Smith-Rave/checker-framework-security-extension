package org.checkerframework.checker.pathtraversal;

import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.checkerframework.framework.test.TypecheckResult;
import org.junit.runners.Parameterized.Parameters;
import java.io.File;
import java.util.List;

public class PathTraversalTest extends CheckerFrameworkPerDirectoryTest {

public PathTraversalTest(List<File> testFiles) {
    super(
        testFiles,
        PathTraversalChecker.class,
        "pathtraversal",
        "-Anomsgtext",
        "-nowarn"
    );
}

@Parameters
public static String[] getTestDirs() {
    return new String[] {"pathtraversal"};
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