import org.checkerframework.checker.commandinjection.qual.TaintedCommand;
import org.checkerframework.checker.commandinjection.qual.UntaintedCommand;
import java.lang.reflect.Method;

public class CommandInjectionTestCases {

    void testVulnerableRuntime(@TaintedCommand String userInput) throws Exception {
        // :: error: (argument)
        Runtime.getRuntime().exec(userInput);
    }

    void testSafeRuntime(@UntaintedCommand String safeCmd) throws Exception {
        Runtime.getRuntime().exec(safeCmd);
    }

    void testVulnerableProcessBuilder(@TaintedCommand String userInput) throws Exception {
        // :: error: (argument)
        ProcessBuilder pb = new ProcessBuilder(userInput);
    }

    void testSafeProcessBuilder(@UntaintedCommand String safeCmd) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(safeCmd);
    }

    // Evasion proof: Reflective Method.invoke bypasses @UntaintedCommand check (0 compiler errors)
    void testCommandReflectionEvasion(@TaintedCommand String userInput) throws Exception {
        String maliciousCmd = "ping -c 1 " + userInput;
        Method execMethod = Runtime.class.getMethod("exec", String.class);
        execMethod.invoke(Runtime.getRuntime(), maliciousCmd);
    }
}