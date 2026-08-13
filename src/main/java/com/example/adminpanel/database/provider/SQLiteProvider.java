package com.example.adminpanel.database.provider;

import com.example.adminpanel.AdminPanelPlugin;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLiteProvider {
    private final AdminPanelPlugin plugin;
    private HikariDataSource dataSource;

    public SQLiteProvider(AdminPanelPlugin plugin) { this.plugin = plugin; }

    public void initialize() {
        File file = new File(plugin.getDataFolder(), "adminpanel.db");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:" + file.getAbsolutePath());
        config.setMaximumPoolSize(1);
        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException { return dataSource.getConnection(); }
    public void shutdown() { if (dataSource != null && !dataSource.isClosed()) dataSource.close(); }
}
