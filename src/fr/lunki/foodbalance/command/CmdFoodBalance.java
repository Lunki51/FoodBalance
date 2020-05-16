package lunki.modify.foodbalance.command;

import lunki.modify.foodbalance.Broadcast;
import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.Message;
import lunki.modify.foodbalance.SBA.SAbout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdFoodBalance implements CommandExecutor {
    public static FoodBalance plugin;

    public CmdFoodBalance(FoodBalance instance) {
        plugin = instance;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("foodbalance")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reset")) {
                    if (args.length > 1) {
                        try {
                            Player player = org.bukkit.Bukkit.getPlayer(args[1]);
                            plugin.getValueManager().resetValues(player);
                            sender.sendMessage(Broadcast.TAG + Broadcast.getMsg(Message.VALUES_RESET).replace("%player%",
                                    player.getName()));
                        } catch (Exception e) {
                            sender.sendMessage(Broadcast.getMsg(Message.USAGE_RESET));
                        }
                    } else {
                        sender.sendMessage(Broadcast.getMsg(Message.USAGE_RESET));
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    try {
                        plugin.reloadConfig();
                        plugin.reloadData();
                        sender.sendMessage(Broadcast.TAG + Broadcast.getMsg(Message.RELOAD_OK).replace("%version%",
                                plugin.getDescription().getVersion()));
                    } catch (Exception e) {
                        sender.sendMessage(Broadcast.TAG + Broadcast.getMsg(Message.RELOAD_FAIL).replace("%version%",
                                plugin.getDescription().getVersion()));
                    }
                }
                if (args[0].equalsIgnoreCase("help")) {
                    if (args.length > 1) {
                        try {
                            Player player = org.bukkit.Bukkit.getPlayer(args[1]);
                            sender.sendMessage("//WIP//");
                        } catch (Exception e) {
                            sender.sendMessage(Broadcast.getMsg(Message.USAGE_RESET));
                        }
                    } else {
                        sender.sendMessage(Broadcast.getMsg(Message.USAGE_RESET));
                    }
                }
                if (args[0].equalsIgnoreCase("feed")) {
                    plugin.getData().set("values.protein." + Bukkit.getPlayer(args[1]).getUniqueId(), 7);
                    plugin.getData().set("values.carbohydrate." + Bukkit.getPlayer(args[1]).getUniqueId(), 7);
                    plugin.getData().set("values.vitamin." + Bukkit.getPlayer(args[1]).getUniqueId(), 7);
                    plugin.getData().set("values.water." + Bukkit.getPlayer(args[1]).getUniqueId(), 1800);
                    sender.sendMessage("Toutes les données du joueur on été mises au maximum");
                }
                if (args[0].equalsIgnoreCase("set")) {
                    if (args[1].equalsIgnoreCase("protein")) {
                        if (args.length > 2) {
                            try {
                                plugin.getData().set("values.protein." + Bukkit.getPlayer(args[2]).getUniqueId(), Integer.valueOf(args[3]));
                                sender.sendMessage("La donnée protéines du joueur " + args[2] + " a été mise à " + args[3]);
                            } catch (Exception e) {
                                sender.sendMessage("Player Not Found");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("carbohydrate")) {
                        if (args.length > 2) {
                            try {
                                plugin.getData().set("values.carbohydrate." + Bukkit.getPlayer(args[2]).getUniqueId(), Integer.valueOf(args[3]));
                                sender.sendMessage("La donnée glucides du joueur " + args[2] + " a été mise à " + args[3]);
                            } catch (Exception e) {
                                sender.sendMessage("Player Not Found");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("vitamin")) {
                        if (args.length > 2) {
                            try {
                                plugin.getData().set("values.vitamin." + Bukkit.getPlayer(args[2]).getUniqueId(), Integer.valueOf(args[3]));
                                sender.sendMessage("La donnée vitamines du joueur " + args[2] + " a été mise à " + args[3]);
                            } catch (Exception e) {
                                sender.sendMessage("Player Not Found");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("water")) {
                        if (args.length > 2) {
                            try {
                                plugin.getData().set("values.water." + Bukkit.getPlayer(args[2]).getUniqueId(), Integer.valueOf(args[3]));
                                sender.sendMessage("La donnée hydratation du joueur " + args[2] + " a été mise à " + args[3]);
                            } catch (Exception e) {
                                sender.sendMessage("Player Not Found");
                            }
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("off")) {
                    if (args.length > 2) {
                        if (args[1].equalsIgnoreCase("water")) {
                            if (args[2].equalsIgnoreCase("all")) {
                                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                    plugin.getValueManager().addtoWaterBPList(player);
                                    sender.sendMessage("Set the water off for all players");
                                }
                            } else {
                                plugin.getValueManager().addtoWaterBPList(Bukkit.getServer().getPlayer(args[2]));
                                sender.sendMessage("Set the water off for " + args[2]);
                            }
                        }
                        if (args[1].equalsIgnoreCase("nutrient")) {
                            if (args[2].equalsIgnoreCase("all")) {
                                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                    plugin.getValueManager().addtoNutrientBPList(player);
                                    sender.sendMessage("Set the nutrient off for all players");
                                }
                            } else {
                                plugin.getValueManager().addtoNutrientBPList(Bukkit.getServer().getPlayer(args[2]));
                                sender.sendMessage("Set the nutrient off for " + args[2]);
                            }
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("on")) {
                    if (args.length > 2) {
                        if (args[1].equalsIgnoreCase("water")) {
                            if (args[2].equalsIgnoreCase("all")) {
                                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                    plugin.getValueManager().removefromWaterBPList(player);
                                    sender.sendMessage("Set the water on for all players");
                                }
                            } else {
                                plugin.getValueManager().removefromWaterBPList(Bukkit.getServer().getPlayer(args[2]));
                                sender.sendMessage("Set the water on for " + args[2]);
                            }
                        }
                        if (args[1].equalsIgnoreCase("nutrient")) {
                            if (args[2].equalsIgnoreCase("all")) {
                                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                                    plugin.getValueManager().removefromNutrientBPList(player);
                                    sender.sendMessage("Set the nutrient on for all players");
                                }
                            } else {
                                plugin.getValueManager().removefromNutrientBPList(Bukkit.getServer().getPlayer(args[2]));
                                sender.sendMessage("Set the nutrient on for " + args[2]);
                            }
                        }
                    }
                }


            } else {
                showAbout(sender, true);
            }
        } else {

            showAbout(sender, false);
        }

        return false;
    }

    public void showAbout(CommandSender sender, boolean perms) {
        SAbout about = new SAbout();
        sender.sendMessage(Broadcast.TAG + about.getAuthor());
        sender.sendMessage(Broadcast.TAG + "Current version: " + plugin.getDescription().getVersion());
        if (perms) {
            sender.sendMessage(Broadcast.TAG + "Use " + ChatColor.GREEN + "/fb help " +
                    ChatColor.GOLD + "to get a list of commands.");
        }
    }
}
