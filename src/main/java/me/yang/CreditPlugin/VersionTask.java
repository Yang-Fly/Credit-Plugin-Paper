package me.yang.CreditPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class VersionTask extends BukkitRunnable {
    private final CreditPlugin plugin;
    private final CommandSender sender;

    public VersionTask(CreditPlugin plugin, CommandSender sender) {
        this.sender = sender;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // TODO VER
        sender.sendMessage("This is VersionTask.");
    }
}
