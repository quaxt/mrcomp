package com.quaxt.mcc.asm;

import com.quaxt.mcc.Op;

import java.io.PrintWriter;

public record Unary(Op op, Operand operand) implements Instruction {

    @Override
    public void emitAsm(PrintWriter out) {
        throw new RuntimeException("Not Implemented");
       // out.println("        movl	$" + i + ", %eax");
    }
}
