package com.quaxt.mcc.asm;

import com.quaxt.mcc.Op;
import com.quaxt.mcc.tacky.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.quaxt.mcc.asm.Nullary.RET;

public class Codegen {
    public static ProgramAsm convertToAsm(ProgramIr program) {
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
                    instructionAsms.add(new Mov(src, Reg.EAX));
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
            case IntIr(int i) -> new Imm(i);
            case VarIr(String identifier) -> new Pseudo(identifier);
        };
    }


    public static void replacePseudoRegisters(ProgramAsm programAsm) {
        replacePseudoRegisters(programAsm.functionAsm());
    }

    private static Operand dePseudo(Operand in, Map<String, Integer> varTable, AtomicInteger offset) {
        return switch (in) {
            case Imm _, Reg _, Stack _ -> in;
            case Pseudo(String identifier) -> {
                Integer varOffset = varTable.computeIfAbsent(identifier, (k) -> offset.getAndAdd(-4));
                yield new Stack(varOffset);
            }
        };
    }

    private static void replacePseudoRegisters(FunctionAsm functionAsm) {
        List<Instruction> instructions = functionAsm.instructions();
        AtomicInteger offset = new AtomicInteger(-4);
        Map<String, Integer> varTable = new HashMap<>();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction oldInst = instructions.get(i);
            Instruction newInst = switch (oldInst) {
                case AllocateStack _, Nullary _ -> oldInst;
                case Mov(Operand src, Operand dst) ->
                        new Mov(dePseudo(src, varTable, offset), dePseudo(dst, varTable, offset));
                case Unary(Op op, Operand operand) -> new Unary(op, dePseudo(operand, varTable, offset));
            };
            instructions.set(i, newInst);
        }
    }
}
