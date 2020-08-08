package me.yang.CreditPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.util.Objects;

public final class CreditPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("------------------------------");
        Bukkit.getLogger().info("Welcome to use CGCredit system");
        Bukkit.getLogger().info("     Loading up CGCredit      ");
        Bukkit.getLogger().info("------------------------------");
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(this), this);
        CommandsExecutor executor = new CommandsExecutor(this);
        Objects.requireNonNull(this.getCommand("cgcredit")).setExecutor(executor);
        Objects.requireNonNull(this.getCommand("cgcredit")).setTabCompleter(executor);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
        Bukkit.getLogger().info("---------------------------------------");
        Bukkit.getLogger().info("Thank you for using the CGCredit system");
        Bukkit.getLogger().info("      Now shutting down CGCredit       ");
        Bukkit.getLogger().info("---------------------------------------");
        HandlerList.unregisterAll(this);
    }

}

