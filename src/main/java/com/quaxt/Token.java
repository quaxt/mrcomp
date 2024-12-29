package com.quaxt;

import java.util.EnumMap;

public class Token {
    String value = null;
    TokenType type = null;
    static EnumMap<TokenType, Token> fixed = new EnumMap<>(TokenType.class);

    static {
        for (TokenType t:TokenType.values()){
            if (!t.hasValue()){
                fixed.put(t, new Token(t, null));

            }
        }

    }

    private Token(TokenType tokenType, String value) {
        this.type = tokenType;
        this.value = value;
    }

    public static Token of(TokenType tokenType) {
        if (tokenType == null) throw new IllegalArgumentException("TokenType must not be null");
        Token t = fixed.get(tokenType);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(tokenType + " requires value");
    }

    public static Token of(TokenType tokenType, String value) {
        return new Token(tokenType, value);
    }

    public String toString() {
        if (type.hasValue()) {
            return type.toString() + "=" + value;
        }
        return type.toString();

    }
}
