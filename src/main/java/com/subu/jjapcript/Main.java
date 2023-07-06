package com.subu.jjapcript;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.ArrayList;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        File dataFolder = new File("dataFile");
        ArrayList<File> jjcFiles = getJJCFiles(dataFolder);

        getCommandMap().register("flies", new Command("flies") {
            @Override
            public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                getLogger().info("Flies does");
                return true;
            }
        }.setDescription("Flies").setUsage("/flies"));
    }

    /**
     * Get command map to register command without plugin.yml editing
     * @return command map from declared field
     * @since version 0.1
     */
    private SimpleCommandMap getCommandMap() {
        try {
            java.lang.reflect.Field commandMapField = getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (SimpleCommandMap) commandMapField.get(getServer());
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get .jjc files on data folder
     * @param dataFolder data folder of plugin
     * @return .jjc files
     * @since version 0.1
     */
    private static ArrayList<File> getJJCFiles(File dataFolder) {
        File[] files = dataFolder.listFiles();
        ArrayList<File> jjcFiles = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jjc")) {
                    jjcFiles.add(file);
                } else if (file.isDirectory()) {
                    jjcFiles.addAll(getJJCFiles(file));
                }
            }
        }

        return jjcFiles;
    }
}
