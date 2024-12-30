package com.quaxt.asm;

import java.io.PrintStream;
import java.io.PrintWriter;

public record Mov(int i) implements Instruction {

    @Override
    public void emitAsm(PrintWriter out) {
        out.println("        movl	$" + i + ", %eax");
    }
}
