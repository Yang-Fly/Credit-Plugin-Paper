package me.yang.CreditPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class SendTask extends BukkitRunnable {
    private final CommandSender sender;
    private final CreditPlugin plugin;
    private final String text;

    public SendTask(CreditPlugin plugin, CommandSender sender, String text) {
        this.plugin = plugin;
        this.sender = sender;
        this.text = text;
    }

    @Override
    public void run() {
        if (sender.getName().equals("CONSOLE")) {
            sender.sendMessage(text);
        } else {
            sender.sendMessage(text);
            plugin.getLogger().info(text);
        }
    }
}
