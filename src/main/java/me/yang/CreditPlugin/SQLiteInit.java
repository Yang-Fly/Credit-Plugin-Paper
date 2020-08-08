package me.yang.CreditPlugin;

import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class SQLiteInit extends BukkitRunnable {
    private final CreditPlugin plugin;
    private lib.PatPeter.SQLibrary.SQLite sqlite;

    public SQLiteInit(CreditPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        sqlConnection();
        try {
            sqlTableCheck();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void sqlConnection() {
        sqlite = new lib.PatPeter.SQLibrary.SQLite(plugin.getLogger(),
                "Plugin Name",
                plugin.getDataFolder().getAbsolutePath(),
                "database");
//Make sure sqlite is the same as the variable you specified at the top of the plugin!
        try {
            sqlite.open();
        } catch (Exception e) {
            plugin.getLogger().info(e.getMessage());
            plugin.getPluginLoader().disablePlugin(plugin);
        }
    }

    public void sqlTableCheck() throws SQLException {
        if(sqlite.isTable("CGCredit")){
            return;
        } else {
            sqlite.query("CREATE TABLE CGCredit (PLAYERNAME TEXT PRIMARY KEY NOT NULL, SCORE INT NOT NULL, REGDATE TEXT NOT NULL);");
            plugin.getLogger().info("CGCredit sqlite-table has been created");
        }
    }
}
