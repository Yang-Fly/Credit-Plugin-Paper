package me.yang.CreditPlugin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SQLiteWrite extends BukkitRunnable {
    private final CreditPlugin plugin;
    private CommandSender sender;
    private lib.PatPeter.SQLibrary.SQLite sqlite;

    private java.lang.String name;
    private int score = 0;
    private java.lang.String regdate;
    private java.lang.String[] args = new String[0];

    public SQLiteWrite(CreditPlugin plugin, CommandSender sender, java.lang.String[] args, java.lang.String name) { //Full or Clear
        this.plugin = plugin;
        this.sender = sender;
        this.args = args;
        this.name = name;
    }

    public SQLiteWrite(CreditPlugin plugin, CommandSender sender, java.lang.String[] args, java.lang.String name, int score) { //add reduce set
        this.plugin = plugin;
        this.sender = sender;
        this.args = args;
        this.name = name;
        this.score = score;
    }

    public SQLiteWrite(CreditPlugin plugin, java.lang.String name) { //PlayerLoginTask
        this.plugin = plugin;
        this.name = name;
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

    // TODO SQLiteWrite

    public void sqlWriteIn() throws SQLException {
        sqlite.query("INSERT INTO CGCredit(PLAYERNAME, SCORE, REGDATE) VALUES(" + name + ", " + score + ", " + regdate + ");"); //This is optional. You can do this later if you want.
    }

    public void sqlUpdateWithoutDate() throws SQLException {
        sqlite.query("UPDATE CGCredit SET SCORE = " + score + " WHERE NAME = " + name); //This is optional. You can do this later if you want.
    }

    public void setScore() {
        if (score == 0 && args.length == 1 && args[0].equals("full")) {
            score = plugin.getConfig().getInt("Full-score");
            return;
        }
        score = plugin.getConfig().getInt("Default-score");
    }

    public void setDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        regdate = df.format(new Date());// new Date()为获取当前系统时间
    }

    @Override
    public void run() {
        sqlConnection();
        if (score == 0) {
            setScore();
        }
        if (args.length != 0) {
            try {
                sqlUpdateWithoutDate();
                new SendTask(plugin, sender, "Write to sqlite database successful.Player \"" + name + "\"'s score has been set to " + score + ".");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                new SendTask(plugin, sender, "Write to sqlite database failed.");
            }
        }
        if (args.length == 0) {
            setDate();
            try {
                sqlWriteIn();
                Bukkit.getLogger().info("Player " + name + "'s score has been set to Default");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Bukkit.getLogger().info("Player " + name + "'s score might already been in the database");
            }
        }
    }
}
