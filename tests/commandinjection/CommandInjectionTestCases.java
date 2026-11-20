import org.checkerframework.checker.commandinjection.qual.TaintedCommand;
import org.checkerframework.checker.commandinjection.qual.UntaintedCommand;

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
}