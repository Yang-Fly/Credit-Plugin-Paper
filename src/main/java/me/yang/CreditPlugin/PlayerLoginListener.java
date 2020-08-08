package me.yang.CreditPlugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public final class PlayerLoginListener implements Listener {
    private final CreditPlugin plugin;

    public PlayerLoginListener(CreditPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Bukkit.getLogger().info("Player " + event.getPlayer().getName() + " is logging in!");
        new PlayerLoginTask(plugin, event.getPlayer().getName()).runTaskAsynchronously(plugin);
    }
}
