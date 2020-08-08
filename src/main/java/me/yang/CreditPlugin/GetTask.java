package me.yang.CreditPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class GetTask extends BukkitRunnable {
    private final CreditPlugin plugin;
    private final CommandSender sender;
    private String username;

    public GetTask(CreditPlugin plugin, CommandSender sender) {
        this.plugin = plugin;
        this.sender = sender;
    }

    public GetTask(CreditPlugin plugin, CommandSender sender, String username) {
        this.plugin = plugin;
        this.sender = sender;
        this.username = username;
    }

    @Override
    public void run() {
        // TODO GET
        sender.sendMessage("This is GetTask.");
    }
}
