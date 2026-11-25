import org.checkerframework.checker.pathtraversal.qual.TaintedPath;
import org.checkerframework.checker.pathtraversal.qual.UntaintedPath;
import java.io.File;
import java.lang.reflect.Constructor;

public class PathTraversalTestCases {

    void testVulnerablePath(@TaintedPath String userInput) throws Exception {
        // :: error: (argument)
        File f = new File(userInput);
    }

    void testSafePath(@UntaintedPath String safePath) throws Exception {
        File f = new File(safePath);
    }

    // Evasion proof: Reflective Constructor.newInstance bypasses @UntaintedPath check (0 compiler errors)
    void testPathReflectionEvasion(@TaintedPath String userInput) throws Exception {
        String maliciousPath = "../../etc/" + userInput;
        Constructor<File> fileCtor = File.class.getConstructor(String.class);
        File f = fileCtor.newInstance(maliciousPath);
    }
}