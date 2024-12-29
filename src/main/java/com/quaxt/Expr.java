package com.quaxt;

sealed interface Expr extends Node {
}

record Int(int i) implements Expr {
}
