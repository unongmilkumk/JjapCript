package com.subu.jjapcript;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {
    public static void compile(String code) {

    }

    public static ArrayList<Jjapcript> splitCode(String code) {
        ArrayList<Jjapcript> codes = new ArrayList<>();
        findTypes("Command", code).forEach((key, value) -> {

        });
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

    public static Map<String, String> findTypes(String type, String input) {
        Map<String, String> commands = new HashMap<>();
        Pattern pattern = Pattern.compile(type + " \\{[^}]*?}", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String command = matcher.group();
            String key = command.substring(command.indexOf('(') + 1, command.indexOf(')')).trim();
            String value = command.substring(command.indexOf('{') + 1, command.length() - 1);
            commands.put(key, value);
        }
        return commands;
    }
}