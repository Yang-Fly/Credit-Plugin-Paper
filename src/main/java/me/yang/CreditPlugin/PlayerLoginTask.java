package me.yang.CreditPlugin;

import org.bukkit.scheduler.BukkitRunnable;

public class PlayerLoginTask extends BukkitRunnable {
    private final String username;
    private final CreditPlugin plugin;

    public PlayerLoginTask(CreditPlugin plugin, String username) {
        this.plugin = plugin;
        this.username = username;
    }

    @Override
    public void run() {
        new SQLiteWrite(plugin, username).runTaskAsynchronously(plugin);
    }
}
