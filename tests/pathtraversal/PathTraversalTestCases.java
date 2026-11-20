import org.checkerframework.checker.pathtraversal.qual.TaintedPath;
import org.checkerframework.checker.pathtraversal.qual.UntaintedPath;
import java.io.File;

public class PathTraversalTestCases {

    void testVulnerablePath(@TaintedPath String userInput) throws Exception {
        // :: error: (argument)
        File f = new File(userInput);
    }

    void testSafePath(@UntaintedPath String safePath) throws Exception {
        File f = new File(safePath);
    }
}