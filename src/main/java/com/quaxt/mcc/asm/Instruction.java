package com.quaxt.mcc.asm;

import java.io.PrintWriter;

public sealed interface Instruction permits AllocateStack, Mov, Nullary, Unary {
    void emitAsm(PrintWriter out);
    default String formatOperand(Operand o) {
        return switch (o) {
            case Imm(int i) -> "$" + i;
            case Pseudo pseudo -> null;
            case Reg reg -> "%" + reg;
            case Stack(int offset) -> offset + "(%rbp)";
        };
    }
    default void printIndent(PrintWriter out, String s) {
        out.println("\t" + s);
    }
}
