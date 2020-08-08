package me.yang.CreditPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class ReloadTask extends BukkitRunnable {
    private final CreditPlugin plugin;
    private final CommandSender sender;

    public ReloadTask(CreditPlugin plugin, CommandSender sender) {
        this.plugin = plugin;
        this.sender = sender;
    }

    @Override
    public void run() {
        try {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.AQUA + "Reload Config Successful!");
        } catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "Reload Config Failed!");
        }
    }
}
