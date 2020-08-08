package me.yang.CreditPlugin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class CommandsExecutor implements TabExecutor {
    private final CreditPlugin plugin;
    private final List<String> SubCommands = new ArrayList<>();

    public CommandsExecutor(CreditPlugin plugin) {
        this.plugin = plugin;
        new SQLiteInit(plugin).runTaskAsynchronously(plugin);
        SubCommands.add("help"); //all                         player
        SubCommands.add("version"); //all                      player
        SubCommands.add("get"); //可name, admin show name      player, admin
        SubCommands.add("top"); //all                          player
        SubCommands.add("add"); //可name, admin only           admin
        SubCommands.add("reduce"); //可name, admin only        admin
        SubCommands.add("set"); //可name, admin only           admin
        SubCommands.add("full"); //可name, admin only          admin
        SubCommands.add("clear"); //可name, admin only         admin
        SubCommands.add("reload"); //reload config             admin
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        if (sender.hasPermission("cgcredit.admin")) {
            if (args.length == 1 && args[0].equals("reload")) {
                new ReloadTask(plugin, sender).runTaskAsynchronously(plugin);
                return true;
            }
            if (args.length == 2 && args[0].equals("get")) {
                new GetTask(plugin, sender, args[1]).runTaskAsynchronously(plugin);
                return true;
            }
            if (args.length == 2 && args[0].equals("add")) {
                new AddTask(plugin, sender, args[1]).runTaskAsynchronously(plugin);
                return true;
            }
            if (args.length == 2 && args[0].equals("reduce")) {
                new ReduceTask(plugin, sender, args[1]).runTaskAsynchronously(plugin);
                return true;
            }
            if (args.length == 2 && args[0].equals("set")) {
                new SetTask(plugin, sender, args[1]).runTaskAsynchronously(plugin);
                return true;
            }
            if (args.length == 2 && args[0].equals("full")) {
                new FullTask(plugin, sender, args[1]).runTaskAsynchronously(plugin);
                return true;
            }
            if (args.length == 2 && args[0].equals("clear")) {
                new ClearTask(plugin, sender, args[1]).runTaskAsynchronously(plugin);
                return true;
            }
        }
        if (sender.hasPermission("CGCredit.player")) {
            if (args.length == 0) {
                return false;
            }
            if (args.length == 1 && args[0].equals("help")) {
                return false;
            }
            if (args.length == 1 && args[0].equals("version")) {
                new VersionTask(plugin, sender).runTaskAsynchronously(plugin);
                return true;
            }
            if (args.length == 1 && args[0].equals("get")) {
                new GetTask(plugin, sender).runTaskAsynchronously(plugin);
                return true;
            }
            if (args.length == 1 && args[0].equals("top")) {
                new TopTask(plugin, sender).runTaskAsynchronously(plugin);
                return true;
            }
            sender.sendMessage("Unknown command. Type \"/cgcredit help\" or \"/cgcredit\" for help.");
            return true;
        }
        sender.sendMessage("You do not have permission to execute this command!");
        StringBuilder builder = new StringBuilder();
        builder.append(cmd);
        for (String arg : args) {
            builder.append(" ");
            builder.append(arg);
        }
        plugin.getLogger().warning(sender.getName() + " tried to execute command: " + builder);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            result.addAll(SubCommands);
        } else if (args.length == 2) {
            if (args[0].equals("get") || args[0].equals("add") || args[0].equals("reduce") ||args[0].equals("set") || args[0].equals("full") || args[0].equals("clear")) {
                if (sender.hasPermission("cgcredit.admin")) {
                    for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                        String name = player.getName();
                        if (name != null) {
                            result.add(name);
                        }
                    }
                }
            }
        }
        return result;
    }
}
