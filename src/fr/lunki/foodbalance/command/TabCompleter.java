package lunki.modify.foodbalance.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> list = new ArrayList<>();
        if (commandSender instanceof Player) {
            if (command.getName().equalsIgnoreCase("foodbalance")) {
                if (strings.length == 1) {
                    list.add("set");
                    list.add("feed");
                    list.add("on");
                    list.add("off");
                    return list;
                }
                if (strings[0].equalsIgnoreCase("on") || strings[0].equalsIgnoreCase("off") && strings.length < 3) {
                    list.add("water");
                    list.add("cold");
                    list.add("nutrient");
                    return list;
                }
                if (strings[0].equalsIgnoreCase("set")) {
                    if (strings.length == 2) {
                        list.add("water");
                        list.add("cold");
                        list.add("protein");
                        list.add("vitamin");
                        list.add("carbohydrate");
                        return list;
                    }
                    if (strings.length == 3) {
                        return null;
                    }
                    if (strings.length == 4) {
                        if (strings[1].equalsIgnoreCase("water")) {
                            list.add("0");
                            list.add("1800");
                            return list;
                        } else {
                            list.add("-7");
                            list.add("0");
                            list.add("7");
                            return list;
                        }
                    }
                }
            }
        }
        return null;
    }
}
