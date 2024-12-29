package com.quaxt;

import java.util.regex.Pattern;

public enum TokenType {
    IDENTIFIER("[a-zA-Z_]\\w*\\b"),
    OPEN_PAREN("\\("), CLOSE_PAREN("\\)"),
    OPEN_BRACE("\\{"), CLOSE_BRACE("\\}"),NUMERIC("[0-9]+"),SEMICOLON(";");

    final Pattern regex;

    TokenType(String pattern) {
        this.regex = Pattern.compile(pattern);
    }

    public boolean hasValue() {
        return this == IDENTIFIER||this == NUMERIC;
    }
}
