package com.subu.jjapcript;

import java.util.ArrayList;
import java.util.Arrays;

public class Compiler {
    public static void compile(String code) {

    }

    public static ArrayList<Jjapcript> splitCode(String code) {
        ArrayList<Jjapcript> codes = new ArrayList<>();
        return codes;
    }

    /**
     * remove 4 space or tabs to look easy
     * @param code which remove 4 space or tabs
     * @return removed code
     * @since version 0.1
     */
    public static ArrayList<String> removeTab(String code) {
        ArrayList<String> lines = (ArrayList<String>) Arrays.stream(code.split("\n")).toList();
        ArrayList<String> newlines = new ArrayList<>();
        for (String line : lines) {
            newlines.add(line.replaceFirst("^( {4}|\\t)", ""));
        }
        return newlines;
    }
}