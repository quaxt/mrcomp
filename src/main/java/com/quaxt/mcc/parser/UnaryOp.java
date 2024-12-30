package com.quaxt.mcc.parser;

sealed public interface UnaryOp extends Exp permits Complement, Negate {
}

