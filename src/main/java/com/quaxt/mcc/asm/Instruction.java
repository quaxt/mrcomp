package com.quaxt.mcc.asm;

import java.io.PrintWriter;

public sealed interface Instruction permits AllocateStack, Mov, Nullary, ReturnAsm, Unary {
    void emitAsm(PrintWriter out);

    default void printIndent(PrintWriter out, String s) {
        out.println("        " + s);
    }
}
