package me.yang.CreditPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class AddTask extends BukkitRunnable {
    private final CreditPlugin plugin;
    private final CommandSender sender;
    private String username;

    public AddTask(CreditPlugin plugin, CommandSender sender, String username) {
        this.plugin = plugin;
        this.sender = sender;
        this.username = username;
    }

    @Override
    public void run() {
        // TODO ADD
        sender.sendMessage("This is AddTask.");
    }
}
