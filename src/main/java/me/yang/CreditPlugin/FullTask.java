package me.yang.CreditPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class FullTask extends BukkitRunnable {
    private final CreditPlugin plugin;
    private final CommandSender sender;
    private String username;

    public FullTask(CreditPlugin plugin, CommandSender sender, String username) {
        this.plugin = plugin;
        this.sender = sender;
        this.username = username;
    }

    @Override
    public void run() {
        // TODO FULL
        sender.sendMessage("This is FullTask.");
    }
}
