package com.quaxt.asm;

import java.io.PrintStream;
import java.io.PrintWriter;

public sealed interface Instruction permits Mov, ReturnAsm {
    void emitAsm(PrintWriter out);
}
