package com.quaxt.asm;

import java.io.PrintStream;
import java.io.PrintWriter;

public record ReturnAsm() implements Instruction {
    @Override
    public void emitAsm(PrintWriter out) {
        out.println("                ret");
    }
}
