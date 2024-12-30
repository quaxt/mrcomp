package com.quaxt.mcc.parser;

import com.quaxt.mcc.Token;

public record Function(String name, Token returnType, Statement statement) {
}

