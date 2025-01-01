package com.quaxt.mcc.tacky;

import com.quaxt.mcc.BinaryOperator;
public record BinaryIr(BinaryOperator op, ValIr v1, ValIr v2, VarIr dstName) implements InstructionIr {
}

