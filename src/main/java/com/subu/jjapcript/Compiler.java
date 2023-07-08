package com.subu.jjapcript;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {
    public static void compile(String code) {
        ArrayList<Jjapcript> jjapcripts = splitCode(code);
        for (Jjapcript jjapcript : jjapcripts) {
            Bukkit.getLogger().info(jjapcript.compileType.name());
            if (jjapcript.compileType.equals(CompileType.COMMAND)) {
                Bukkit.getLogger().info(jjapcript.text1);
                Main.cm.register(jjapcript.text1.replaceFirst("/", ""), new Command(jjapcript.text1.replaceFirst("/", "")) {
                    @Override
                    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                        findExpression(jjapcript.code).forEach(value -> {
                            if (value.startsWith("log ")) {
                                Bukkit.getLogger().info(value.replaceFirst("log ", ""));
                            }
                        });
                        return true;
                    }
                });
            }
        }
    }

    /**
     * Split Code to Compile Easier
     * @param code Code to split
     * @return Separated Code
     * @since version 0.1
     */
    public static ArrayList<Jjapcript> splitCode(String code) {
        ArrayList<Jjapcript> codes = new ArrayList<>();
        findTypes("Command", code).forEach((key, value) -> codes.add(new Jjapcript(CompileType.COMMAND, key, removeTab(value))));
        findTypes("Event", code).forEach((key, value) -> codes.add(new Jjapcript(CompileType.EVENT, key, removeTab(value))));
        findTypes("Loop", code).forEach((key, value) -> codes.add(new Jjapcript(CompileType.EVENT, key, removeTab(value))));

        return codes;
    }

    /**
     * remove 4 space or tabs to look easy
     * @param code which remove 4 space or tabs
     * @return removed code
     * @since version 0.1
     */
    public static String removeTab(String code) {
        List<String> lines = Arrays.stream(code.split("\n")).toList();
        StringBuilder news = new StringBuilder();
        for (String line : lines) {
            news.append(line.replaceFirst("^( {4}|\\t)", ""));
        }
        return news.toString();
    }

    /**
     * Find types at the code and map it
     * @param type Which to find at the code
     * @param input Code that Find types and map
     * @return Mapped Code
     * @since version 0.1
     */
    public static Map<String, String> findTypes(String type, String input) {
        Map<String, String> commands = new HashMap<>();
        Pattern pattern = Pattern.compile(type + " \\( [^)]* \\) \\{[^}]*?}", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        Bukkit.getLogger().info("Finding Types of " + type);
        while (matcher.find()) {
            String command = matcher.group();
            String key = command.substring(command.indexOf('(') + 1, command.indexOf(')')).trim();
            String value = command.substring(command.indexOf('{') + 1, command.length() - 1);
            Bukkit.getLogger().info(key + " is " + value);
            commands.put(key, value);
        }
        return commands;
    }
    /**
     * Find expression at the code and list it
     * @param input Code that Find expression and list
     * @return Listed Code
     * @since version 0.1
     */
    public static ArrayList<String> findExpression(String input) {
        ArrayList<String> strings = new ArrayList<>();
        input.lines().forEach(line -> {
            if (line.startsWith("#")) {
                strings.add(line.replaceFirst("#", ""));
            }
        });
        return strings;
    }
}