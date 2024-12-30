package com.quaxt.mcc.asm;

import java.io.PrintWriter;

public record ReturnAsm() implements Instruction {
    @Override
    public void emitAsm(PrintWriter out) {
        out.println("                ret");
    }
}
