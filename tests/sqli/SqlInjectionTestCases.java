import org.checkerframework.checker.sqli.qual.Tainted;
import org.checkerframework.checker.sqli.qual.Untainted;
import org.checkerframework.checker.sqli.util.SqlSanitizer;
import java.sql.Statement;

public class SqlInjectionTestCases {

void testVulnerableQuery(Statement stmt, @Tainted String userInput) throws Exception {
    // :: error: (argument)
    stmt.executeQuery("SELECT * FROM users WHERE id = '" + userInput + "'");
}

void testSanitizedQuery(Statement stmt, @Tainted String userInput) throws Exception {
    // Sanitizer converts @Tainted -> @Untainted
    String cleanInput = SqlSanitizer.sanitize(userInput);
    stmt.executeQuery("SELECT * FROM users WHERE id = '" + cleanInput + "'");
}

void testSafeQuery(Statement stmt, @Untainted String safeInput) throws Exception {
    stmt.executeQuery("SELECT * FROM users WHERE id = '" + safeInput + "'");
}
}