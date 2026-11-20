import org.checkerframework.checker.sqli.qual.Tainted;
import org.checkerframework.checker.sqli.qual.Untainted;
import java.sql.Statement;
import java.sql.Connection;

public class SqlInjectionTestCases {

    void testVulnerableSql(Statement stmt, @Untainted String userInput) throws Exception {
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        // :: error: (sqli.sink.violation)
        stmt.executeQuery(query);
    }

    void testSafeSql(Statement stmt, @Tainted String sanitizedInput) throws Exception {
        stmt.executeQuery(sanitizedInput);
    }

    void testPreparedStatement(Connection conn, String userInput) throws Exception {
        // Parameterized queries avoid SQLi sinks
        conn.prepareStatement("SELECT * FROM users WHERE username = ?");
    }
}