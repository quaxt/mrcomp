package com.quaxt.mcc.asm;

import com.quaxt.mcc.Op;

import java.io.PrintWriter;

public record AllocateStack(int i) implements Instruction {

    @Override
    public void emitAsm(PrintWriter out) {
        out.println("        movl	$" + i + ", %eax");
    }
}
