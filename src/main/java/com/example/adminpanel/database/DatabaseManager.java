package com.example.adminpanel.database;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.database.provider.SQLiteProvider;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private final AdminPanelPlugin plugin;
    private SQLiteProvider provider;

    public DatabaseManager(AdminPanelPlugin plugin) { this.plugin = plugin; }

    public void initialize() {
        this.provider = new SQLiteProvider(plugin);
        this.provider.initialize();
    }

    public Connection getConnection() throws SQLException { return provider.getConnection(); }
    public void shutdown() { if (provider != null) provider.shutdown(); }
}
