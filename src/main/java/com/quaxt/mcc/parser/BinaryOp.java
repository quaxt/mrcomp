package com.quaxt.mcc.parser;

import com.quaxt.mcc.BinaryOperator;

public record BinaryOp(BinaryOperator op, Exp left, Exp right) implements Exp {
}