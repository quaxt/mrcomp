package com.quaxt;

import java.util.List;

public class Parser {

    private static List<Token> expect(Token expected, List<Token> tokens) {
        Token token = tokens.removeFirst();
        if (expected != token) {
            throw new IllegalArgumentException("Expected " + expected + ", got " + token);
        }
        return tokens;
    }

    static Expr parseStatement(List<Token> tokens) {
        expect(Token.RETURN, tokens);
        Expr statement = parseExpr(tokens);
        expect(Token.SEMICOLON, tokens);
        return statement;
    }

    private static Expr parseExpr(List<Token> tokens) {
        Token token = tokens.removeFirst();
        if (TokenType.NUMERIC != token.type()){
            throw new IllegalArgumentException("Expected int, got " + token);
        }
        return new Int(Integer.parseInt(token.value()));
    }


    static Node parse(List<Token> tokens) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(parseStatement(Lexer.lex("return 42;")));
    }
}
