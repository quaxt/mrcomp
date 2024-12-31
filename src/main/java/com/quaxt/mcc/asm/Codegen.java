package com.quaxt.mcc.asm;

import com.quaxt.mcc.Op;
import com.quaxt.mcc.tacky.*;

import java.util.ArrayList;
import java.util.List;

import static com.quaxt.mcc.asm.Nullary.RET;

public class Codegen {
    public static ProgramAsm codeGenProgram(ProgramIr program) {
        return new ProgramAsm(codeGenFunction(program.function()));
    }

    private static FunctionAsm codeGenFunction(FunctionIr function) {
        return new FunctionAsm(function.name(), codeGenInstructions(function.instructions()));
    }

    private static List<Instruction> codeGenInstructions(List<InstructionIr> instructions) {
        List<Instruction> instructionAsms = new ArrayList<>();
        for (InstructionIr inst : instructions) {
            switch (inst) {
                case ReturnInstructionIr(ValIr val) -> {
                    Operand src = toOperand(val);
                    instructionAsms.add(new Mov(src, Reg.AX));
                    instructionAsms.add(RET);
                }
                case UnaryIr(Op op, ValIr src, ValIr dstIr) -> {
                    Operand dst = toOperand(dstIr);
                    instructionAsms.add(new Mov(toOperand(src), dst));
                    instructionAsms.add(new Unary(op, dst));
                }
            }
        }
        return instructionAsms;
    }

    private static Operand toOperand(ValIr val) {
        return switch (val) {
            case IntIr(int i)-> new Imm(i);
            case VarIr(String identifier)  -> new Pseudo(identifier);
        };
    }


}
