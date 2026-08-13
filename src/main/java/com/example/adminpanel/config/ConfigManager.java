package com.example.adminpanel.config;

import com.example.adminpanel.AdminPanelPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private final AdminPanelPlugin plugin;
    private final Map<String, FileConfiguration> configs = new HashMap<>();

    public ConfigManager(AdminPanelPlugin plugin) { this.plugin = plugin; }

    public void loadConfigurations() {
        load("config.yml");
        load("messages.yml");
        load("gui.yml");
        load("staff.yml");
        load("database.yml");
        load("discord.yml");
    }

    private void load(String name) {
        File file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) plugin.saveResource(name, false);
        configs.put(name, YamlConfiguration.loadConfiguration(file));
    }

    public FileConfiguration getConfig() { return configs.get("config.yml"); }
    public FileConfiguration getDatabase() { return configs.get("database.yml"); }
    public FileConfiguration getDiscord() { return configs.get("discord.yml"); }
}
