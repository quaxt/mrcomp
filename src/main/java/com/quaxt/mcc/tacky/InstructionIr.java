package com.quaxt.mcc.tacky;

public sealed interface InstructionIr permits BinaryIr, ReturnInstructionIr, UnaryIr {
}
