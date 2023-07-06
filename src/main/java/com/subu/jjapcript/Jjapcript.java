package com.subu.jjapcript;

public class Jjapcript {
    CompileType compileType;
    String code;

    public Jjapcript(CompileType compileType, String code) {
        this.compileType = compileType;
        this.code = code;
    }

    public CompileType getCompileType() {
        return compileType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCompileType(CompileType compileType) {
        this.compileType = compileType;
    }
}
