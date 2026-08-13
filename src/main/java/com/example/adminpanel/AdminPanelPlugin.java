package com.example.adminpanel;

import com.example.adminpanel.config.ConfigManager;
import com.example.adminpanel.database.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public final class AdminPanelPlugin extends JavaPlugin {
    private static AdminPanelPlugin instance;
    private ConfigManager configManager;
    private DatabaseManager databaseManager;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        getLogger().info("==================================================");
        getLogger().info(" AdminPanel Platform - Yukleniyor...");
        getLogger().info("==================================================");

        try {
            // 1. DataFolder (plugins/AdminPanel/) klasorunu zorunlu olustur
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }

            // 2. Konfigurasyon dosyalarini diske cikar ve yukle
            this.configManager = new ConfigManager(this);
            this.configManager.loadConfigurations();

            // 3. Veritabanini baslat
            this.databaseManager = new DatabaseManager(this);
            this.databaseManager.initialize();

            getLogger().info("[AdminPanel] Konfigurasyon klasorleri ve veritabani diskte olusturuldu!");

        } catch (Throwable throwable) {
            getLogger().log(Level.SEVERE, "[AdminPanel] Baslatma esnasinda kritik hata!", throwable);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        getLogger().info("[AdminPanel] Yukleme " + elapsedTime + "ms icinde tamamlandi.");
    }

    @Override
    public void onDisable() {
        if (this.databaseManager != null) {
            this.databaseManager.shutdown();
            this.databaseManager = null;
        }
        this.configManager = null;
        instance = null;
    }

    public static AdminPanelPlugin getInstance() { return instance; }
    public ConfigManager getConfigManager() { return configManager; }
    public DatabaseManager getDatabaseManager() { return databaseManager; }
}
