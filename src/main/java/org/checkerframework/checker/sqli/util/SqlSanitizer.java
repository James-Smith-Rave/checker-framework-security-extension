package org.checkerframework.checker.sqli.util;

public class SqlSanitizer {
public static String sanitize(String input) {
    if (input == null) {
        return null;
    }
    return input.replace("'", "''");
}
}