package com.quaxt.mcc.tacky;

import com.quaxt.mcc.BinaryOperator;
import com.quaxt.mcc.UnaryOperator;
import com.quaxt.mcc.parser.*;

import java.util.ArrayList;
import java.util.List;


public class IrGen {
    public static ProgramIr programIr(Program program) {
        List<InstructionIr> instructions = new ArrayList<>();
        emitInstructions(program.function(), instructions);
        //
        FunctionIr f = new FunctionIr(program.function().name(), program.function().returnType(), instructions);
        return new ProgramIr(f);
    }

    private static void emitInstructions(Function function, List<InstructionIr> instructions) {
        emitInstructions(function.statement(), instructions);
        FunctionIr f = new FunctionIr(function.name(), function.returnType(), instructions);

    }

    private static void emitInstructions(Statement statement, List<InstructionIr> instructions) {
        switch (statement) {
            case Return r: {
                ValIr retVal = emitInstructions(r.exp(), instructions);
                ReturnInstructionIr ret = new ReturnInstructionIr(retVal);
                instructions.add(ret);
            }
        }
    }

    private static ValIr emitInstructions(Exp expr, List<InstructionIr> instructions) {
        switch (expr) {
            case Int(int i): {
                    return new IntIr(i);

            }
            case UnaryOp(UnaryOperator op, Exp exp): {
                ValIr src = emitInstructions(exp, instructions);
                VarIr dst = makeTemporary();
                instructions.add(new UnaryIr(op, src, dst));
                return dst;
            }
            case BinaryOp(BinaryOperator op, Exp left, Exp right):
                ValIr v1 = emitInstructions(left, instructions);
                ValIr v2 = emitInstructions(left, instructions);
                VarIr dstName = makeTemporary();
                instructions.add(new BinaryIr(op, v1, v2, dstName));
                return dstName;
        }
    }

    private static VarIr makeTemporary() {
        return VarIr.newTemprary();
    }

}
