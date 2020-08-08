package me.yang.CreditPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class TopTask extends BukkitRunnable {
    private final CreditPlugin plugin;
    private final CommandSender sender;

    public TopTask(CreditPlugin plugin, CommandSender sender) {
        this.plugin = plugin;
        this.sender = sender;
    }
    @Override
    public void run() {
        // TODO TOP
        sender.sendMessage("This is TopTask.");
    }
}
