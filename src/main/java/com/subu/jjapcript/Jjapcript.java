package com.subu.jjapcript;

public class Jjapcript {
    CompileType compileType;
    String code;
    String text1;

    public Jjapcript(CompileType compileType, String text1, String code) {
        this.text1 = text1;
        this.compileType = compileType;
        this.code = code;
    }

    public CompileType getCompileType() {
        return compileType;
    }

    public String getCode() {
        return code;
    }

    public String getText1() {
        return text1;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCompileType(CompileType compileType) {
        this.compileType = compileType;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }
}
