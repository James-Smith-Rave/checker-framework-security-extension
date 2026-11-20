package org.checkerframework.checker.sqli.util;

import org.checkerframework.checker.sqli.qual.Tainted;
import org.checkerframework.checker.sqli.qual.Untainted;

public class SqlSanitizer {
    @SuppressWarnings("sqli")
    public static @Untainted String sanitize(@Tainted String input) {
        if (input == null) {
            return null;
        }
        return input.replace("'", "''");
    }
}