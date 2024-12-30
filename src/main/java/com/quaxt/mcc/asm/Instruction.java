package com.quaxt.mcc.asm;

import java.io.PrintWriter;

public sealed interface Instruction permits Mov, ReturnAsm {
    void emitAsm(PrintWriter out);
}
