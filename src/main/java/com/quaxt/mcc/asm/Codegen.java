package com.quaxt.mcc.asm;

import com.quaxt.mcc.Lexer;
import com.quaxt.mcc.parser.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Codegen {
    public static ProgramAsm codeGenProgram(Program program) {
        ProgramAsm prog = new ProgramAsm(codeGenFunction(program.function()));
        return prog;
    }

    private static FunctionAsm codeGenFunction(Function function) {

        return new FunctionAsm(function.name(), codeGenInstructions(function.statement()));
    }

    private static List<Instruction> codeGenInstructions(Statement statement) {
        if (statement instanceof Return(Exp exp)) {
            List<Instruction> instructions = new ArrayList<>(codeGenExpr(exp));
            instructions.add(new ReturnAsm());
            return instructions;
        } else throw new IllegalArgumentException("not done: Expr");

    }

    private static List<Instruction> codeGenExpr(Exp exp) {
        if (exp instanceof Int(int i)) {
            List<Instruction> instructions = new ArrayList<>();
            instructions.add(new Mov(i));
            return instructions;
        } else throw new IllegalArgumentException("not done: Expr");

    }

    public static void main(String[] args) throws IOException {
        Program prog = Parser.parseProgram(Lexer.lex(Files.readString(Paths.get("return_2.c"))));
        ProgramAsm progAsm = Codegen.codeGenProgram(prog);
        System.out.println(progAsm);
    }
}
